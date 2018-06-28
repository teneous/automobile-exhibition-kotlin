package ecommerce.transaction.databean

data class TrPaymentDetailVo (
    var paymentMoney: Int,
    var paymentType: Short,
    var paymentDetail: Long? = null
)
