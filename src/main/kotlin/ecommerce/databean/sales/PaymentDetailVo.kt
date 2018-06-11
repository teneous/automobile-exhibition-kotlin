package ecommerce.databean.sales

data class PaymentDetailVo (
    var paymentMoney: Int,
    var paymentType: Short,
    var paymentDetail: Long? = null
)
