package ecommerce.transaction.service.impl

import ecommerce.common.NEW
import ecommerce.common.VALID
import ecommerce.transaction.databean.TrPlaceOrderInfoVo
import ecommerce.transaction.entity.OrderProduct
import ecommerce.transaction.entity.OrderSheet
import ecommerce.customer.repository.ICustomerRepository
import ecommerce.transaction.repository.IOrderProductRepository
import ecommerce.transaction.repository.IOrderSheetRepository
import ecommerce.transaction.service.IPlaceOrderService
import ecommerce.transaction.service.ITrGenerateSequenceNoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.stream.Collectors
import javax.transaction.Transactional

@Service
@Transactional
class PlaceOrderServiceImpl: IPlaceOrderService{

    @Autowired
    lateinit var customerRepository: ICustomerRepository
    @Autowired
    lateinit var orderProductRepository: IOrderProductRepository
    @Autowired
    lateinit var orderSheetRepository: IOrderSheetRepository
    @Autowired
    lateinit var timeSequenceNoServiceImpl: ITrGenerateSequenceNoService

    override fun placeOrder(orderInfo: TrPlaceOrderInfoVo) {
        //数据校验
        if(!checkPlaceOrder(orderInfo)) return
        val now = LocalDateTime.now()
        //计算每个商品的金额以及折扣
        val totalProductMoney = orderInfo.product_list.stream().collect(Collectors.summingInt {it.market_price*it.number })//商品市场总价
        val totalProductDiscount = orderInfo.calculateDiscountAndGetTotal()//获取商品所有折扣
        val totalDiscount = orderInfo.special_discount?.sumBy { it.second }?.plus(totalProductDiscount)//获取该订单内所有折扣

        val customerCode = timeSequenceNoServiceImpl.generateSequenceNo()
        val sellerCode = timeSequenceNoServiceImpl.generateSellerSequenceNo(orderInfo.shop_info.shopCode!!)
        //保存订单信息
        OrderSheet().apply {
                sequenceNo = customerCode
                this.sellerCode = sellerCode
                orderTime = now
                status = NEW
                customerId = orderInfo.customer_info.customer_id
                totalMoney =  totalProductMoney?.minus(totalDiscount!!)
                recevieAddress = orderInfo.customer_info.recevie_address
                mobileNo = orderInfo.customer_info.mobile_no
                this.totalDiscount = totalDiscount
        }.let { orderSheetRepository.save(it)}

        //保存订单内的商品信息{restvo->entity,对象之间的转换应该用map更为合适}
        orderInfo.product_list.map {
            OrderProduct(
                    sequenceNo = customerCode,
                    customerId = orderInfo.customer_info.customer_id,
                    totalNum = it.number,
                    productId = it.product_id,
                    status = VALID,
                    discount = it.discount_value,
                    realTotalMoney = it.number * it.market_price - it.discount
            )
        }.let { orderProductRepository.saveAll(it) }
        //如果有特殊折扣商品(特殊折扣卷,将其作为一种商品)
        if (orderInfo.special_discount?.size != 0) {
            orderInfo.special_discount?.map{
                OrderProduct(
                        sequenceNo = customerCode,
                        customerId = orderInfo.customer_info.customer_id,
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
    fun checkPlaceOrder(orderInfo: TrPlaceOrderInfoVo): Boolean{
        val customer = customerRepository.getOne(orderInfo.customer_info.customer_id)
        if(VALID != customer.status) return false
        if (orderInfo.product_list.isEmpty()) return false
        return true
    }
}

    //extends function
    fun TrPlaceOrderInfoVo.calculateDiscountAndGetTotal(): Int{
        var totalDiscount = 0
        //过滤掉不能打折的,在对其分组
        product_list.filter {it.can_discount == 1.toShort()}.forEach{
            totalDiscount += it.discount
        }
        return totalDiscount
    }
