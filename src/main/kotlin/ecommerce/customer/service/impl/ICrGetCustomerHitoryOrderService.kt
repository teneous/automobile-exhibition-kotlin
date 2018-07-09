package ecommerce.customer.service.impl

import ecommerce.common.ShopInfo
import ecommerce.common.enums.errors.TransactionType
import ecommerce.common.util.TimeFormatUtil
import ecommerce.customer.restvo.CrOrderInfoResultVo
import ecommerce.customer.repository.ICustomerRepository
import ecommerce.customer.restvo.CrOrderDetailBaseInfoVo
import ecommerce.customer.restvo.CrOrderDetailInfoResultVo
import ecommerce.transaction.repository.IOrderSheetRepository
import ecommerce.customer.service.ICrGetCustomerHitoryOrderService
import ecommerce.transaction.repository.IOrderProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class CrGetCustomerHitoryOrderServiceImpl: ICrGetCustomerHitoryOrderService {

    @Autowired
    private lateinit var customerRepository: ICustomerRepository
    @Autowired
    private lateinit var orderSheetRepository: IOrderSheetRepository
    @Autowired
    private lateinit var orderProductRepository: IOrderProductRepository

    override fun getAllCustomerHistoryOrder(customerId: Long,pageable: Pageable):List<CrOrderInfoResultVo>  {
        val resultList = ArrayList<CrOrderInfoResultVo>()
        val customer = customerRepository.findById(customerId)
        if(!customer.isPresent) return emptyList()
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
        val orderInfo = CrOrderDetailBaseInfoVo()
        currentOrder.map{
            orderInfo = currentOrder.
            crOrderDetailBaseInfoVo
        }

        val apply = CrOrderDetailInfoResultVo().apply {
            this.order_info=null
            this.customer_info=null
            this.product_info=null
        }




        return


    }
}