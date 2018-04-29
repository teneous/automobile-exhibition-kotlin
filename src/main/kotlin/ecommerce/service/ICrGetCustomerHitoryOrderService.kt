package ecommerce.service

import ecommerce.databean.CrOrderCondition

interface ICrGetCustomerHitoryOrderService {

    fun getAllCustomerHitoryOrder(customerId:Long)

    fun getCustomerHitoryOrder(customerId:Long,searchCondition: CrOrderCondition)
}