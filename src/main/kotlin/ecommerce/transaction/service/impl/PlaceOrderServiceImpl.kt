package ecommerce.transaction.service.impl

import ecommerce.common.databean.UNPAY
import ecommerce.common.databean.VALID
import ecommerce.transaction.databean.TrOrderInfoVo
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

    override fun placeOrder(orderInfo: TrOrderInfoVo) {
        //数据校验
        if(!checkPlaceOrder(orderInfo)) return
        val now = LocalDateTime.now()
        //计算每个商品的金额以及折扣
        val totalProductMoney = orderInfo.productList.stream().collect(Collectors.summingInt {it.marketPrice*it.number })//商品市场总价
        val totalProductDiscount = orderInfo.calculateDiscountAndGetTotal()//获取商品所有折扣
        val totalDiscount = orderInfo.specialDiscount?.sumBy { it.second }?.plus(totalProductDiscount)//获取该订单内所有折扣

        val customerCode = timeSequenceNoServiceImpl.generateSequenceNo()
        val sellerCode = timeSequenceNoServiceImpl.generateSellerSequenceNo(orderInfo.shopInfo.shopCode!!)
        //保存订单信息
        OrderSheet().apply {
                sequenceNo = customerCode
                this.sellerCode = sellerCode
                orderTime = now
                status = UNPAY
                customerId = orderInfo.customerInfo.customerId
                totalMoney =  totalProductMoney?.minus(totalDiscount!!)
                recevieAddress = orderInfo.customerInfo.recevieAddress
                mobileNo = orderInfo.customerInfo.mobileNo
                this.totalDiscount = totalDiscount
        }.let { orderSheetRepository.save(it)}

        //保存订单内的商品信息{restvo->entity,对象之间的转换应该用map更为合适}
        orderInfo.productList.map {
            OrderProduct(
                    sequenceNo = customerCode,
                    customerId = orderInfo.customerInfo.customerId,
                    totalNum = it.number,
                    productId = it.productId,
                    status = VALID,
                    discount = it.discountValue,
                    realTotalMoney = it.number * it.marketPrice - it.discount
            )
        }.let { orderProductRepository.saveAll(it) }
        //如果有特殊折扣商品(特殊折扣卷,将其作为一种商品)
        if (orderInfo.specialDiscount?.size != 0) {
            orderInfo.specialDiscount?.map{
                OrderProduct(
                        sequenceNo = customerCode,
                        customerId = orderInfo.customerInfo.customerId,
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
    fun checkPlaceOrder(orderInfo: TrOrderInfoVo): Boolean{
        val customer = customerRepository.getOne(orderInfo.customerInfo.customerId)
        if(VALID != customer.status) return false
        if (orderInfo.productList.size == 0) return false
        return true
    }
}

    //extends function
    fun TrOrderInfoVo.calculateDiscountAndGetTotal(): Int{
        var totalDiscount = 0
        //过滤掉不能打折的,在对其分组
        productList.filter {it.canDiscount == 1.toShort()}.forEach{
            totalDiscount += it.discount
        }
        return totalDiscount
    }
