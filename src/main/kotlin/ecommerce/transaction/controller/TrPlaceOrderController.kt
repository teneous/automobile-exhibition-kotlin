package ecommerce.transaction.controller

import ecommerce.transaction.databean.TrPlaceOrderInfoVo
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
class TrPlaceOrderController{

    @Autowired
    private lateinit var placeOrderService: IPlaceOrderService
    @Autowired
    private lateinit var payForOrderService: IPayForOrderService

    /**
     * 订单下单
     */
    @PostMapping("/placeorder")
    fun placeOrder(@RequestBody orderInfo: TrPlaceOrderInfoVo){
        placeOrderService.placeOrder(orderInfo)
    }

    /**
     * 支付订单
     */
    @PostMapping("/payfororder")
    fun payForOrder(@RequestBody paymentVo: TrPaymentVo){
        payForOrderService.payForOrder(paymentVo)
    }

}