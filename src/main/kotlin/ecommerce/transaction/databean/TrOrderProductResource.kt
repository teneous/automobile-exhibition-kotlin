package ecommerce.transaction.controller

import ecommerce.transaction.databean.TrOrderInfoVo
import ecommerce.transaction.databean.TrPaymentVo
import ecommerce.transaction.service.IPayForOrderService
import ecommerce.transaction.service.IPlaceOrderService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

/**
 * 订单资源
 */
@RestController
@RequestMapping("/order")
class PlaceOrder{

    @Autowired
    private lateinit var placeOrderService: IPlaceOrderService
    @Autowired
    private lateinit var payForOrderService: IPayForOrderService

    @PostMapping("/placeorder")
    fun placeOrder(@RequestBody orderInfo: TrOrderInfoVo){
        placeOrderService.placeOrder(orderInfo)
    }

    @PostMapping("/payfororder")
    fun payForOrder(@RequestBody paymentVo: TrPaymentVo){
        payForOrderService.payForOrder(paymentVo)
    }

}