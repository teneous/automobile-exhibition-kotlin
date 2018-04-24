package ecommerce.service

import ecommerce.databean.OrderInfoVo
import ecommerce.databean.PaymentVo


interface IPlaceOrderService{

   /**
    * 提交订单
    */
   fun placeOrder(orderInfo: OrderInfoVo)


}