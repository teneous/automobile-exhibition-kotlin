package ecommerce.transaction.service

import ecommerce.transaction.databean.TrPlaceOrderInfoVo


interface IPlaceOrderService{

   /**
    * 提交订单
    */
   fun placeOrder(orderInfo: TrPlaceOrderInfoVo)


}