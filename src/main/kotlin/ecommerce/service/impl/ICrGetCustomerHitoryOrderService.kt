package ecommerce.service.impl

import ecommerce.databean.CrOrderCondition
import ecommerce.entity.Order
import ecommerce.repository.ICustomerRepository
import ecommerce.repository.IOrderRepository
import ecommerce.service.ICrGetCustomerHitoryOrderService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CrGetCustomerHitoryOrderServiceImpl: ICrGetCustomerHitoryOrderService {

    @Autowired
    private lateinit var customerRepository: ICustomerRepository
    @Autowired
    private lateinit var orderRepository: IOrderRepository

    override fun getAllCustomerHitoryOrder(customerId: Long):List<Order>?  {
        val customer = customerRepository.findById(customerId)
        var orderList : List<Order>? = null
        customer.ifPresent {orderList = orderRepository.findByCustomerIdAndStatus(it.id!!)}
        return orderList
    }

    override fun getCustomerHitoryOrder(customerId: Long, searchCondition: CrOrderCondition) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}