package ecommerce.customer.service

import ecommerce.customer.restvo.CrOrderInfo
import ecommerce.transaction.databean.TrCrOrderCondition
import org.springframework.data.domain.Pageable

interface ICrGetCustomerHitoryOrderService {

    /**
     * 获取这个顾客的订单
     */
    fun getAllCustomerHistoryOrder(customerId:Long,pageable: Pageable):List<CrOrderInfo>

    fun getCustomerHistoryOrder(customerId:Long,searchCondition: TrCrOrderCondition)
}