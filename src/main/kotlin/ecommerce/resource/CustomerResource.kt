package ecommerce.resource

import ecommerce.databean.order.CrOrderCondition
import ecommerce.entity.OrderSheet
import ecommerce.service.ICrGetCustomerHitoryOrderService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/customer")
class CustomerResource {

    @Autowired
    private lateinit var crGetCustomerHitoryOrderService: ICrGetCustomerHitoryOrderService

    @GetMapping("/{customerId}/history")
    fun getCustomerHistoryOrder(@PathVariable("customerId") customerId:Long):List<OrderSheet>?{
        return crGetCustomerHitoryOrderService.getAllCustomerHistoryOrder(customerId)
    }

    //请求参数过多的话
    @PostMapping("/{customerId}/history")
    fun getCustomerHistoryOrder(@PathVariable("customerId") customerId:Long,@RequestBody searchVo: CrOrderCondition){
        return crGetCustomerHitoryOrderService.getCustomerHistoryOrder(customerId,searchVo)
    }
}
