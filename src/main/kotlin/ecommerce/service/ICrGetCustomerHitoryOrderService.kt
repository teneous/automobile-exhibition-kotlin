package ecommerce.service

import ecommerce.databean.CrOrderCondition
import ecommerce.entity.Order

interface ICrGetCustomerHitoryOrderService {

    fun getAllCustomerHitoryOrder(customerId:Long):List<Order>?

    fun getCustomerHitoryOrder(customerId:Long,searchCondition: CrOrderCondition)
}