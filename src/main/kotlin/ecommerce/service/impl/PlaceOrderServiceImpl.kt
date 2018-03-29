package ecommerce.service.impl

import ecommerce.databean.OrderInfo
import ecommerce.repository.customer.ICustomerRepository
import ecommerce.repository.transaction.IOrderRepository
import ecommerce.service.IPlaceOrderService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PlaceOrderServiceImpl: IPlaceOrderService{

    @Autowired
    lateinit var customerRepository: ICustomerRepository
    @Autowired
    lateinit var orderRepository: IOrderRepository

    override fun placeOrder(orderInfo: OrderInfo) {
        if(checkRequestData(orderInfo)) return
        orderInfo.customerId
    }

    /**
     * 顾客数据校验
     */
    fun checkRequestData(orderInfo: OrderInfo): Boolean{
        customerRepository.getOne(orderInfo.customerId!!)
        return true
    }
}