package ecommerce.service

import ecommerce.databean.sales.PaymentVo

/**
 * Created by syoka on 2018/4/24.
 */
interface IPayForOrderService{

    /**
     * 订单付款
     */
    fun payForOrder(paymentVo: PaymentVo)
}