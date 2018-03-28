package ecommerce.resource

import ecommerce.databean.OrderInfo
import ecommerce.service.IPlaceOrderService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController("/v1/order")
class PlaceOrder{

    @Autowired
    private lateinit var placeOrderService: IPlaceOrderService

    @PostMapping("/placeorder")
    fun placeOrder(@RequestBody orderInfo: OrderInfo):String{
        placeOrderService.placeOrder(orderInfo)
        return "hellow"
    }
}