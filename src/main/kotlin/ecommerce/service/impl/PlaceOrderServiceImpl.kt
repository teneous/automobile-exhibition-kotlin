package ecommerce.service.impl

import ecommerce.common.*
import ecommerce.common.extendfun.abs
import ecommerce.common.extendfun.times
import ecommerce.databean.OrderDiscountVo
import ecommerce.databean.OrderInfoVo
import ecommerce.entity.customer.OrderProduct
import ecommerce.entity.transaction.Order
import ecommerce.repository.customer.ICustomerRepository
import ecommerce.repository.customer.IOrderProductRepository
import ecommerce.repository.transaction.IOrderRepository
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
        val discountVo = orderInfo.discountVo
        //计算每个商品的金额以及折扣
        val totalProductDiscount = orderInfo.calculateDiscountAndGetTotal(discountVo)//商品所有折扣
        val totalDiscount = orderInfo.discountVo?.specialProduct?.sumBy { it.second }?.plus(totalProductDiscount)//本订单所有折扣
        val totalProductMoney = orderInfo.productList?.fold(0,{number,price-> price * number})
        //保存订单信息
        Order().apply {
                orderTime = now
                status = UNPAY
                sequenceNo = orderInfo.sequenceNo
                customerId = orderInfo.customerId
                this.totalDiscount = totalDiscount
                totalMoney =  totalProductMoney?.minus(totalDiscount!!)
        }.let { orderRepository.save(it)}
        //保存订单商品表{vo->entity,对象之间的转换应该用map更为合适}
        orderInfo.productList?.map { OrderProduct(
                sequenceNo = orderInfo.sequenceNo,
                customerId = orderInfo.customerId,
                totalNum = it.number,
                productId = it.productId,
                status = VALID,
                discount = it.discount,
                realTotalMoney = it.number * it.marketPrice - it.discount
        )}.let { orderProductRepository.saveAll(it?: listOf()) }
        //如果有特殊折扣商品则插入一条特殊商品
        if (orderInfo.discountVo?.specialProduct?.size != 0) {
            orderInfo.discountVo?.specialProduct?.forEach{
                OrderProduct().apply {
                    sequenceNo = orderInfo.sequenceNo
                    customerId = orderInfo.customerId
                    productId = it.first
                    totalNum = 1
                    status = VALID
                    discount = it.second
                    realTotalMoney = 0
                }.let { orderProductRepository.save(it)}
            }
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
    fun OrderInfoVo.calculateDiscountAndGetTotal(discountVo: OrderDiscountVo?): Int{
        var totalDiscount = 0
        productList?.filter {it.canDiscount == 1.toShort()}?.map {
            when (discountVo?.discountType) {
                DISCOUNT_RATE -> {
                    it.discount = it.number.times(it.marketPrice).times(discountVo.doscountValue).div(100).abs()
                    totalDiscount += it.discount
                }
                DISCOUNT_MONEY -> {
                    it.discount = (it.marketPrice * it.number * discountVo.doscountValue)
                    totalDiscount += it.discount
                }
                else -> {
                    it.discount = 0
                }
            }
        }
        return totalDiscount
    }

