package ecommerce.transaction.service.impl

import ecommerce.common.enums.errors.TrErrorInfoEnum
import ecommerce.common.enums.status.OrderStatusEnum
import ecommerce.transaction.databean.TrPaymentVo
import ecommerce.transaction.entity.Sale
import ecommerce.transaction.entity.DealsPayment
import ecommerce.transaction.entity.OrderSheet
import ecommerce.transaction.repository.IDealsPaymentRepository
import ecommerce.transaction.repository.IOrderSheetRepository
import ecommerce.transaction.repository.ISaleRepository
import ecommerce.transaction.service.IPayForOrderService
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

    override fun payForOrder(paymentVo: TrPaymentVo) {
        val currentOrder = orderSheetRepository.findBySequenceNo(paymentVo.sequenceNo)
        /**金钱校验**/
        if (checkPayForOrder(currentOrder, paymentVo) != null) return

        //保存交易记录
        val now = LocalDateTime.now()
        val sale = Sale()
        sale.apply {
             saleTimes = now
             money = paymentVo.paymentDetail.sumBy { it.paymentMoney.intValueExact() } //实际支付金额
             discount = currentOrder.totalDiscount?.intValueExact()
        }.let { saleRepository.save(it) }

        /**保存支付金钟状态**/
        paymentVo.paymentDetail.map {
            DealsPayment(
                saleId = sale.id,
                paymentType = it.paymentType,
                payMoney = it.paymentMoney.intValueExact(),
                paymentDetailId = it.paymentDetail
            )
        }.let { dealsPaymentRepotory.saveAll(it) }

        /**变更订单状态**/
        currentOrder.apply {
            status = OrderStatusEnum.WAIT.code
        }.let { orderSheetRepository.save(currentOrder) }
    }

    /**
     * 对当前订单进行校验
     */
    fun checkPayForOrder(currentOrder: OrderSheet, paymentVo: TrPaymentVo): TrErrorInfoEnum? {
        //判断订单是否已经过期
        if ((OrderStatusEnum.EXPIRE.equals(currentOrder.status))) {
            return TrErrorInfoEnum.ORDER_OUT_OF_DATE
        }
        //订单表的实际支付总额等于红包加上客户支付金额
        val orderMoney = currentOrder.totalMoney?.toInt()
        val payMoney = paymentVo.paymentDetail.sumBy { it.paymentMoney.toInt() }
        if (orderMoney !=payMoney) {
            return TrErrorInfoEnum.ORDER_ACTUALPAY_NOT_EQUAL_ORDERMONEY
        }
        return null
    }
}