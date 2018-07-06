package ecommerce.customer.service

import ecommerce.customer.restvo.CrOrderDetailInfoResultVo
import ecommerce.customer.restvo.CrOrderInfoResultVo
import org.springframework.data.domain.Pageable

interface ICrGetCustomerHitoryOrderService {

    /**
     * 获取这个顾客的订单
     */
    fun getAllCustomerHistoryOrder(customerId:Long,pageable: Pageable):List<CrOrderInfoResultVo>

    /**
     * 获取订单详细
     */
    fun getOrderDetailInfo(sequenceNo:String): CrOrderDetailInfoResultVo
}