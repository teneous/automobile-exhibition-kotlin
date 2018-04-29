package ecommerce.resource

import ecommerce.databean.CrOrderCondition
import ecommerce.databean.OrderInfoVo
import ecommerce.service.ICrGetCustomerHitoryOrderService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/customer")
class CustomerResource {

    @Autowired
    private lateinit var crGetCustomerHitoryOrderServic: ICrGetCustomerHitoryOrderService

    @GetMapping("/{customerId}/history")
    fun getCustomerHistoryOrder(@PathVariable("customerId") customerId:Long){
        return crGetCustomerHitoryOrderServic.getAllCustomerHitoryOrder(customerId)
    }

    //请求参数过多的话
    @PostMapping("/{customerId}/history")
    fun getCustomerHistoryOrder(@PathVariable("customerId") customerId:Long,@RequestBody searchVo: CrOrderCondition){
        return crGetCustomerHitoryOrderServic.getCustomerHitoryOrder(customerId,searchVo)
    }
}
