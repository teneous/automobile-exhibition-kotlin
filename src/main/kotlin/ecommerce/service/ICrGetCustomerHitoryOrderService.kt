package ecommerce.service

import ecommerce.databean.order.CrOrderCondition
import ecommerce.entity.OrderSheet

interface ICrGetCustomerHitoryOrderService {

    fun getAllCustomerHistoryOrder(customerId:Long):List<OrderSheet>?

    fun getCustomerHistoryOrder(customerId:Long,searchCondition: CrOrderCondition)
}