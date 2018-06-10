package ecommerce.service.impl

import ecommerce.common.EXPIRED
import ecommerce.common.ErrorInfo
import ecommerce.common.PAY
import ecommerce.databean.PaymentVo
import ecommerce.entity.Sale
import ecommerce.entity.DealsPayment
import ecommerce.entity.OrderSheet
import ecommerce.repository.IDealsPaymentRepository
import ecommerce.repository.IOrderSheetRepository
import ecommerce.repository.ISaleRepository
import ecommerce.service.IPayForOrderService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime

/**
 * Created by syoka on 2018/4/24.
 */
@Service
class PayForOrderServiceImpl: IPayForOrderService{
    @Autowired
    lateinit var orderSheetRepository: IOrderSheetRepository
    @Autowired
    lateinit var saleRepository: ISaleRepository
    @Autowired
    lateinit var dealsPaymentRepotory: IDealsPaymentRepository

    override fun payForOrder(paymentVo: PaymentVo) {
        val currentOrder = orderSheetRepository.findBySequenceNo(paymentVo.sequenceNo)
        //金钱校验
        if (checkPayForOrder(currentOrder, paymentVo) != null) return

        //保存交易记录
        val now = LocalDateTime.now()
        //暂时没有想到其他办法可以拿到它的id
        val sale = Sale()
        sale.apply {
             saleTimes = now
             money = paymentVo.paymentDetail.sumBy { it.paymentMoney } //实际支付金额
             otherPay = paymentVo.redPacket.sumBy { it.second }
             discount = currentOrder.totalDiscount
        }.let { saleRepository.save(it) }

        //保存支付金钟状态
        paymentVo.paymentDetail.map {
            DealsPayment(
                saleId = sale.id,
                paymentType = it.paymentType,
                payMoney = it.paymentMoney,
                paymentDetailId = it.paymentDetail
            )
        }.let { dealsPaymentRepotory.saveAll(it) }

        //设置为已支付状态
        currentOrder.apply {
            status = PAY
        }.let { orderSheetRepository.save(currentOrder) }
    }

    /**
     * 对当前订单进行校验
     */
    fun checkPayForOrder(currentOrder: OrderSheet, paymentVo: PaymentVo): ErrorInfo? {
        //判断订单是否已经过期
        if ((EXPIRED == currentOrder.status)) {
            return ErrorInfo.ORDER_HAS_BENN_EXPIRED
        }
        //订单表的实际支付总额等于红包加上客户支付金额
        if (currentOrder.totalMoney != paymentVo.paymentDetail.sumBy { it.paymentMoney }.plus(paymentVo.redPacket.sumBy { it.second })) {
            return ErrorInfo.ORDER_ACTUALPAY_NOT_EQUAL_TO_REAL_PAY
        }
        return null
    }
}