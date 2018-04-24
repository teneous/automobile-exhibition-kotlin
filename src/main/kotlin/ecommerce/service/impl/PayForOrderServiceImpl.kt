package ecommerce.service.impl

import ecommerce.common.EXPIRED
import ecommerce.common.ErrorInfo
import ecommerce.databean.PaymentVo
import ecommerce.entity.transaction.Order
import ecommerce.repository.customer.IOrderProductRepository
import ecommerce.repository.transaction.IOrderRepository
import ecommerce.service.IPayForOrderService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Created by syoka on 2018/4/24.
 */
@Service
class PayForOrderServiceImpl: IPayForOrderService{
    @Autowired
    lateinit var orderRepository: IOrderRepository
    @Autowired
    lateinit var orderProductRepository: IOrderProductRepository

    override fun payForOrder(paymentVo: PaymentVo) {
        val currentOrder = orderRepository.findBySequenceNo(paymentVo.sequenceNo)
        //金钱校验
        if (checkPayForOrder(currentOrder, paymentVo) != null) return
    }


    fun checkPayForOrder(currentOrder: Order, paymentVo: PaymentVo): ErrorInfo? {
        //判断订单是否已经过期
        if ((EXPIRED == currentOrder.paymentType)) {
            return ErrorInfo.ORDER_HAS_BENN_EXPIRED
        }
        return null
    }
}