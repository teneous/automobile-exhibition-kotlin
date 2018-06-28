package ecommerce.customer.service.impl

import ecommerce.common.databean.ShopInfo
import ecommerce.customer.databean.CrOrderInfo
import ecommerce.transaction.databean.TrCrOrderCondition
import ecommerce.customer.repository.ICustomerRepository
import ecommerce.transaction.repository.IOrderSheetRepository
import ecommerce.customer.service.ICrGetCustomerHitoryOrderService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class CrGetCustomerHitoryOrderServiceImpl: ICrGetCustomerHitoryOrderService {

    @Autowired
    private lateinit var customerRepository: ICustomerRepository
    @Autowired
    private lateinit var orderSheetRepository: IOrderSheetRepository

    override fun getAllCustomerHistoryOrder(customerId: Long,pageable: Pageable):List<CrOrderInfo>  {
        var resultList = ArrayList<CrOrderInfo>()
        val customer = customerRepository.findById(customerId)
        val basicalList = orderSheetRepository.listCrOrderBasicalDto(customerId,pageable)
        val baseInfo = basicalList.groupBy { it.sequenceNo }
        basicalList.forEach{
            CrOrderInfo().apply {
                this.sequenceNo = it.sequenceNo
                this.shopInfo = ShopInfo().apply {
                    shopCode = it.shopCode
                    shopName = it.shopName
                }
                this.paymoney = it.payMoney
            }.let {resultList.add(it)}
        }
        return resultList
    }

    override fun getCustomerHistoryOrder(customerId: Long, searchCondition: TrCrOrderCondition) {

    }
}