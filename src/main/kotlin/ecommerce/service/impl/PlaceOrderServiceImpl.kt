package ecommerce.service.impl

import ecommerce.common.*
import ecommerce.common.extendfun.abs
import ecommerce.common.extendfun.times
import ecommerce.databean.OrderInfoVo
import ecommerce.entity.OrderProduct
import ecommerce.entity.Order
import ecommerce.repository.ICustomerRepository
import ecommerce.repository.IOrderProductRepository
import ecommerce.repository.IOrderRepository
import ecommerce.service.IPlaceOrderService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class PlaceOrderServiceImpl: IPlaceOrderService{

    @Autowired
    lateinit var customerRepository: ICustomerRepository
    @Autowired
    lateinit var orderProductRepository: IOrderProductRepository
    @Autowired
    lateinit var orderRepository: IOrderRepository

    override fun placeOrder(orderInfo: OrderInfoVo) {
        if(!checkPlaceOrder(orderInfo)) return
        val now = LocalDateTime.now()
        //计算每个商品的金额以及折扣
        val totalProductMoney = orderInfo.productList?.fold(0,{number,price-> price * number})//商品市场总价
        val totalProductDiscount = orderInfo.calculateDiscountAndGetTotal()//获取商品所有折扣
        val totalDiscount = orderInfo.specialDiscount?.sumBy { it.second }?.plus(totalProductDiscount)//获取该订单内所有折扣
        //保存订单信息
        Order().apply {
                sequenceNo = orderInfo.sequenceNo
                orderTime = now
                status = UNPAY
                customerId = orderInfo.customerId
                this.totalDiscount = totalDiscount
                totalMoney =  totalProductMoney?.minus(totalDiscount!!)
        }.let { orderRepository.save(it)}
        //保存订单内的商品信息{vo->entity,对象之间的转换应该用map更为合适}
        orderInfo.productList?.map {
            OrderProduct(
                    sequenceNo = orderInfo.sequenceNo,
                    customerId = orderInfo.customerId,
                    totalNum = it.number,
                    productId = it.productId,
                    status = VALID,
                    discount = it.discountValue,
                    realTotalMoney = it.number * it.marketPrice - it.discountValue
            )
        }.let { orderProductRepository.saveAll(it?: listOf()) }
        //如果有特殊折扣商品(特殊折扣卷,将其作为一种商品)
        if (orderInfo.specialDiscount?.size != 0) {
            orderInfo.specialDiscount?.map{
                OrderProduct(
                        sequenceNo = orderInfo.sequenceNo,
                        customerId = orderInfo.customerId,
                        totalNum = 1,
                        status = VALID,
                        productId = it.first,
                        discount = it.second,
                        realTotalMoney = 0
                )}.let { orderProductRepository.saveAll(it?: listOf())}
            }
        }

    /**
     * 下订单数据校验
     */
    fun checkPlaceOrder(orderInfo: OrderInfoVo): Boolean{
        val customer = customerRepository.getOne(orderInfo.customerId ?: return false)
        if(VALID != customer.status) return false
        if (orderInfo.productList?.size == 0) return false
        return true
    }
}

    //extends function
    fun OrderInfoVo.calculateDiscountAndGetTotal(): Int{
        var totalDiscount = 0
//        val discountTotalMoney =  productList?.filter {it.canDiscount == 0.toShort() && DISCOUNT_MONEY == it.discountType}
//                ?.sumBy { it.marketPrice*it.number }
        //过滤掉不能打折的,在对其分组
        productList?.filter {it.canDiscount == 0.toShort()}?.groupBy {it.discountType}?.forEach{
            when(it.key){
                DISCOUNT_RATE -> { //折扣计算
                    it.value.map {
                        it.discount = (it.number * it.marketPrice * it.discountValue.div(100)).abs()
                        totalDiscount += it.discount
                    }
                }
//                DISCOUNT_MONEY -> {//满减计算
//                    it.value.map {
////                        it.discount = (it.marketPrice * it.number / discountTotalMoney)
//                        totalDiscount += it.discount
//                    }
//                }
            }
        }
        return totalDiscount
    }
