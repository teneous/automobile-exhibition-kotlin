package ecommerce.transaction.service.impl

import ecommerce.common.CUSTOMER_VALID
import ecommerce.common.VILID
import ecommerce.common.enums.errors.CrErrorInfoEnum
import ecommerce.common.enums.errors.TrErrorInfoEnum
import ecommerce.common.enums.status.OrderStatusEnum
import ecommerce.common.exception.CustomerException
import ecommerce.customer.entity.Customer
import ecommerce.transaction.databean.TrPlaceOrderInfoVo
import ecommerce.transaction.entity.OrderProduct
import ecommerce.transaction.entity.OrderSheet
import ecommerce.customer.repository.ICustomerRepository
import ecommerce.stock.repository.IProductRepository
import ecommerce.transaction.databean.TrProductInfo
import ecommerce.transaction.entity.Product
import ecommerce.transaction.repository.IOrderProductRepository
import ecommerce.transaction.repository.IOrderSheetRepository
import ecommerce.transaction.service.IPlaceOrderService
import ecommerce.transaction.service.ITrGenerateSequenceNoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.math.BigDecimal
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
    @Autowired
    lateinit var productRepository: IProductRepository

    override fun placeOrder(orderInfo: TrPlaceOrderInfoVo) {
        //1.数据校验
        val currentCustomer = checkPlaceOrder(orderInfo)
        val now = LocalDateTime.now()
        //2.计算订单总额,总割引额
        val productList = productRepository.findByProductIdIn(orderInfo.product_list.stream().
                map { e -> e.product_id }.collect(Collectors.toList()))
        //k:productid,v:购买数量
        val purchaseMap = orderInfo.product_list.groupBy { it.product_id }.
                mapValues { it.value.stream().collect(Collectors.summarizingInt(TrProductInfo::number))}
        //k productid v price
        val productPriceMap = productList.stream().collect(Collectors.toMap(Product::productId, Product::price))
        //原商品总价
        var totalProductMoney = 0L
        productList.stream().forEach{
            totalProductMoney += purchaseMap.getValue(it.productId!!).sum.times(it.price!!.longValueExact())
        }
        //获取商品所有折扣
        val totalProductDiscount = orderInfo.calculateDiscountAndGetTotal().toLong()
        val totalDiscount = orderInfo.special_discount?.sumBy { it.second }?.plus(totalProductDiscount)?:0L//获取该订单内所有折扣
        //3.获取商家码,订单号
        val sequenceNo = timeSequenceNoServiceImpl.generateSequenceNo()
        val sellerCode = timeSequenceNoServiceImpl.generateSellerSequenceNo(orderInfo.shop_code)
        //4.保存订单信息
        OrderSheet().apply {
                this.sequenceNo = sequenceNo
                this.sellerCode = sellerCode
                orderTime = now
                status = OrderStatusEnum.NEW.code
                customerId = currentCustomer.id
                totalMoney =  BigDecimal(totalProductMoney.minus(totalDiscount))
                recevieAddress = orderInfo.customer_info.recevie_address
                mobileNo = orderInfo.customer_info.mobile_no
                this.totalDiscount = BigDecimal(totalDiscount)
                recevierName = orderInfo.customer_info.customer_name
        }.let { orderSheetRepository.save(it)}

        /**
         * 5.保存订单内的商品信息
         * {restvo->entity,对象之间的转换应该用map更为合适}
         */
        orderInfo.product_list.map {
            OrderProduct(
                    sequenceNo = sequenceNo,
                    customerId = currentCustomer.id,
                    totalNum = it.number,
                    productId = it.product_id,
                    status = VILID,
                    discount = it.discount_value,
                    realTotalMoney = productPriceMap.getValue(it.product_id)?.times(BigDecimal(it.number))?.minus(it.discount_value!!)
            )
        }.let { orderProductRepository.saveAll(it) }
    }

    /**
     * 下订单数据校验
     */
    fun checkPlaceOrder(orderInfo: TrPlaceOrderInfoVo): Customer {
        val customer =  customerRepository.findByIdentityNo(orderInfo.customer_info.customer_identity)
        if(CUSTOMER_VALID != customer.status) throw CustomerException(CrErrorInfoEnum.CUSTOMER_STATU_INVALID.errormsg)
        if (orderInfo.product_list.isEmpty()) throw CustomerException(TrErrorInfoEnum.ORDER_PRODUCT_LIST_IS_EMPTY.errormsg)
        return customer
    }
}



//    extends function
    fun TrPlaceOrderInfoVo.calculateDiscountAndGetTotal(): Int{
        var totalDiscount = 0
        //过滤掉不能打折的,在对其分组
        product_list.filter {it.can_discount == 1.toShort()}.forEach{
            totalDiscount += it.discount
        }
        return totalDiscount
    }
