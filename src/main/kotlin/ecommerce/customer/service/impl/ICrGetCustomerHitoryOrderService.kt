package ecommerce.customer.service.impl

import ecommerce.common.ShopInfo
import ecommerce.common.enums.errors.TransactionType
import ecommerce.common.util.TimeFormatUtil
import ecommerce.customer.repository.ICustomerRepository
import ecommerce.customer.restvo.*
import ecommerce.transaction.repository.IOrderSheetRepository
import ecommerce.customer.service.ICrGetCustomerHitoryOrderService
import ecommerce.stock.repository.IProductRepository
import ecommerce.transaction.repository.IOrderProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*
@Service
class CrGetCustomerHitoryOrderServiceImpl: ICrGetCustomerHitoryOrderService {

    @Autowired
    private lateinit var customerRepository: ICustomerRepository
    @Autowired
    private lateinit var orderSheetRepository: IOrderSheetRepository
    @Autowired
    private lateinit var orderProductRepository: IOrderProductRepository
    @Autowired
    private lateinit var productRepository: IProductRepository;

    override fun getAllCustomerHistoryOrder(customerId: Long,pageable: Pageable):List<CrOrderInfoResultVo>  {
        val resultList = ArrayList<CrOrderInfoResultVo>()
        val customer = customerRepository.findById(customerId)
        if(!customer.isPresent) return emptyList()
        /**查询顾客所有订单**/
        val basicalList = orderSheetRepository.listCrOrderBasicalDto(customerId,pageable)
        basicalList.forEach{
            CrOrderInfoResultVo().apply {
                this.sequence_no = it.sequenceNo
                this.shop_info = ShopInfo().apply {
                    shopCode = it.shopCode
                    shopName = it.shopName
                }
                order_type = TransactionType.getName(it.orderType!!)
                this.pay_money = it.payMoney
                this.pay_type = it.status
                this.order_time = TimeFormatUtil.defaultFormatter(it.orderTime!!)
            }.let {resultList.add(it)}
        }
        return resultList
    }

    override fun getOrderDetailInfo(sequenceNo:String):CrOrderDetailInfoResultVo {
        val currentOrder = orderSheetRepository.findBySequenceNo(sequenceNo)
        val productList = orderProductRepository.findBySequenceNo(sequenceNo)

        /**订单详细**/
        val orderDetailInfo = CrOrderDetailBaseInfoVo().apply {
            sequence_no = currentOrder.sequenceNo!!
            seller_code = currentOrder.sellerCode
            order_type = currentOrder.orderType.toString()
            shop_name = currentOrder.sellerShopName
            pay_money = currentOrder.payMoney
            order_time = TimeFormatUtil.defaultFormatter(currentOrder.orderTime?: LocalDateTime.now())
            pay_time =  TimeFormatUtil.defaultFormatter(currentOrder.payTime?: LocalDateTime.now())
            express = "DEMO"
        }
        /**顾客信息**/
        val customerDetailInfo = CrOrderDetailCustomerInfoVo(currentOrder.recevierName!!, currentOrder.mobileNo!!, currentOrder.recevieAddress!!)

        /**购买商品详细信息**/

        val productInfoVo = ArrayList<CrOrderDetailProductInfoVo>()
        productList.forEach {
            val product = productRepository.findByProductId(it.productId)
            productInfoVo.add(CrOrderDetailProductInfoVo(it.productId!!, it.productName ?: "",
                    product.productImg ?: "", it.description ?: ""))
        }
        return CrOrderDetailInfoResultVo().apply {
            order_info = orderDetailInfo
            customer_info = customerDetailInfo
            product_info = productInfoVo
        }
    }
}