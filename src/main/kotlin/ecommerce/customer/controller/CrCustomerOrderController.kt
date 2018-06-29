package ecommerce.customer.controller

import ecommerce.customer.restvo.CrOrderInfo
import ecommerce.transaction.databean.TrCrOrderCondition
import ecommerce.customer.service.ICrGetCustomerHitoryOrderService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.ModelAndView


@RestController
@RequestMapping("/customer")
class CrCustomerOrderController {

    @Autowired
    private lateinit var crGetCustomerHitoryOrderService: ICrGetCustomerHitoryOrderService

    @GetMapping("/{customerId}/history")
    fun getCustomerHistoryOrder(@PathVariable("customerId") customerId:Long,pageable: Pageable):List<CrOrderInfo>?{
        val modelAndView = ModelAndView()
        modelAndView.view
        return crGetCustomerHitoryOrderService.getAllCustomerHistoryOrder(customerId,pageable)
    }

    //请求参数过多的话
    @PostMapping("/{customerId}/history")
    fun getCustomerHistoryOrder(@PathVariable("customerId") customerId:Long,@RequestBody searchVo: TrCrOrderCondition){
        return crGetCustomerHitoryOrderService.getCustomerHistoryOrder(customerId,searchVo)
    }
}
