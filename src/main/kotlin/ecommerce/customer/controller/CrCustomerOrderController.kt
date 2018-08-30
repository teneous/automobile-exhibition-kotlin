package ecommerce.customer.controller

import ecommerce.customer.restvo.CrOrderDetailInfoResultVo
import ecommerce.customer.restvo.CrOrderInfoResultVo
import ecommerce.customer.service.ICrGetCustomerHitoryOrderService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/customer")
class CrCustomerOrderController {

    @Autowired
    private lateinit var crGetCustomerHitoryOrderService: ICrGetCustomerHitoryOrderService

    @GetMapping("/{customer_id}/history")
    fun getCustomerHistoryOrder(@PathVariable("customer_id") customerId:Long,pageable: Pageable):List<CrOrderInfoResultVo>?{
        return crGetCustomerHitoryOrderService.getAllCustomerHistoryOrder(customerId,pageable)
    }

    @PostMapping("/orderdetail/{sequenceNo}")
    fun getCustomerHistoryOrder(@PathVariable("sequenceNo") sequenceNo:String): CrOrderDetailInfoResultVo {
        return crGetCustomerHitoryOrderService.getOrderDetailInfo(sequenceNo)
    }
}
