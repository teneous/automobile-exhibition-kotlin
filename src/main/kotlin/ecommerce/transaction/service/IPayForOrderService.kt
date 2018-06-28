package ecommerce.transaction.service

import ecommerce.transaction.databean.TrPaymentVo

/**
 * Created by syoka on 2018/4/24.
 */
interface IPayForOrderService{

    /**
     * 订单付款
     */
    fun payForOrder(paymentVo: TrPaymentVo)
}