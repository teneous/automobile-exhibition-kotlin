package ecommerce.service.impl

import ecommerce.common.EXPIRED
import ecommerce.common.ErrorInfo
import ecommerce.common.PAY
import ecommerce.databean.PaymentVo
import ecommerce.entity.transaction.Order
import ecommerce.entity.transaction.Sale
import ecommerce.repository.customer.IOrderProductRepository
import ecommerce.repository.transaction.IOrderRepository
import ecommerce.repository.transaction.ISaleRepository
import ecommerce.service.IPayForOrderService
import org.apache.coyote.http11.Constants.a
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import javax.persistence.Column

/**
 * Created by syoka on 2018/4/24.
 */
@Service
class PayForOrderServiceImpl: IPayForOrderService{
    @Autowired
    lateinit var orderRepository: IOrderRepository
    @Autowired
    lateinit var orderProductRepository: IOrderProductRepository
    @Autowired
    lateinit var saleRepository: ISaleRepository

    override fun payForOrder(paymentVo: PaymentVo) {
        val currentOrder = orderRepository.findBySequenceNo(paymentVo.sequenceNo)
        //金钱校验
        if (checkPayForOrder(currentOrder, paymentVo) != null) return

        currentO
        //保存对应金种信息
        Sale().apply {
            var sequenceNo: String? = null,
            var saleTimes: LocalDateTime? = null,
            var productId: Long? = null,
            var money: Int? = 0,
            var number: Int? = 0
        }
        //设置为已支付状态
        currentOrder.apply {
            status = PAY
            paymentType = paymentVo.paymentType
            payementDetailid = paymentVo.paymentDetail
        }.let { orderRepository.save(currentOrder) }
    }


    /**
     * 对当前订单进行校验
     */
    fun checkPayForOrder(currentOrder: Order, paymentVo: PaymentVo): ErrorInfo? {
        //判断订单是否已经过期
        if ((EXPIRED == currentOrder.paymentType)) {
            return ErrorInfo.ORDER_HAS_BENN_EXPIRED
        }
        //订单表的实际支付总额等于红包加上客户支付金额
        if (currentOrder.totalMoney != paymentVo.totalPayment.plus(paymentVo.redPacket.sumBy { it.second })) {
            return ErrorInfo.ORDER_ACTUALPAY_NOT_EQUAL_TO_REAL_PAY
        }
        return null
    }
}