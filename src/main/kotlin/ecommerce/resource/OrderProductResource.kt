package ecommerce.resource

import ecommerce.databean.OrderInfo
import ecommerce.service.IPlaceOrderService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/order")
class PlaceOrder{

    @Autowired
    private lateinit var placeOrderService: IPlaceOrderService

    @PostMapping("/placeorder")
    fun placeOrder(@RequestBody orderInfo: OrderInfo){
        placeOrderService.placeOrder(orderInfo)
    }
}