package ecommerce.transaction.databean

import java.math.BigDecimal

/**
 * 订单明细金种
 */
data class TrPaymentDetailVo (
    var paymentMoney: BigDecimal,
    var paymentType: Short,
    var paymentDetail: Long? = null
)
