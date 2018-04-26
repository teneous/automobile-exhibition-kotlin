package ecommerce.resource

import ecommerce.databean.OrderInfoVo
import ecommerce.databean.PaymentVo
import ecommerce.service.IPayForOrderService
import ecommerce.service.IPlaceOrderService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/order")
class PlaceOrder{

    @Autowired
    private lateinit var placeOrderService: IPlaceOrderService
    @Autowired
    private lateinit var payForOrderService: IPayForOrderService

    @PostMapping("/placeorder")
    fun placeOrder(@RequestBody orderInfo: OrderInfoVo){
        placeOrderService.placeOrder(orderInfo)
    }

    @PostMapping("/payfororder")
    fun payForOrder(@RequestBody paymentVo: PaymentVo){
        payForOrderService.payForOrder(paymentVo)
    }
}