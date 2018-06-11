package ecommerce.service.impl

import ecommerce.databean.order.CrOrderCondition
import ecommerce.entity.OrderSheet
import ecommerce.repository.ICustomerRepository
import ecommerce.repository.IOrderSheetRepository
import ecommerce.service.ICrGetCustomerHitoryOrderService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CrGetCustomerHitoryOrderServiceImpl: ICrGetCustomerHitoryOrderService {

    @Autowired
    private lateinit var customerRepository: ICustomerRepository
    @Autowired
    private lateinit var orderSheetRepository: IOrderSheetRepository

    override fun getAllCustomerHistoryOrder(customerId: Long):List<OrderSheet>?  {
        val customer = customerRepository.findById(customerId)
        var orderList : List<OrderSheet>? = null
        customer.ifPresent {
            orderList = orderSheetRepository.findValidOrderForCustomer(it.id!!)
        }
        return orderList
    }

    override fun getCustomerHistoryOrder(customerId: Long, searchCondition: CrOrderCondition) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}