package ecommerce.service

import ecommerce.databean.order.OrderInfoVo


interface IPlaceOrderService{

   /**
    * 提交订单
    */
   fun placeOrder(orderInfo: OrderInfoVo)


}