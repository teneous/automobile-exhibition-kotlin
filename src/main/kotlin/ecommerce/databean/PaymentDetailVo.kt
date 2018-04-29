package ecommerce.databean

data class PaymentDetailVo (
    var paymentMoney: Int,
    var paymentType: Short,
    var paymentDetail: Long? = null
)
