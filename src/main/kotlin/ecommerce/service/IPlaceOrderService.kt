package ecommerce.service

import ecommerce.databean.OrderInfo
import ecommerce.databean.OrderInfoVo
import ecommerce.databean.PaymentVo
import org.springframework.stereotype.Service


interface IPlaceOrderService{

   /**
    * 提交订单
    */
   fun placeOrder(orderInfo: OrderInfoVo)

   /**
    * 订单付款
    */
   fun payForOrder(paymentVo: PaymentVo)
}