package ecommerce.service

import ecommerce.databean.OrderInfo
import org.springframework.stereotype.Service


interface IPlaceOrderService{

   fun placeOrder(orderInfo: OrderInfo)
}