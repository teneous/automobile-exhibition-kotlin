package ecommerce.transaction.databean

/**
 * 订单明细金种
 */
data class TrPaymentDetailVo (
    var paymentMoney: Int,
    var paymentType: Short,
    var paymentDetail: Long? = null
)
