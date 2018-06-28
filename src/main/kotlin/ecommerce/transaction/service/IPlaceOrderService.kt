package ecommerce.transaction.service

import ecommerce.transaction.databean.TrOrderInfoVo


interface IPlaceOrderService{

   /**
    * 提交订单
    */
   fun placeOrder(orderInfo: TrOrderInfoVo)


}