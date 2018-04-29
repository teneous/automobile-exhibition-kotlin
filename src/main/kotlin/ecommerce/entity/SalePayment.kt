package ecommerce.entity

data class SalePayment(
    var id :Long? = null,
    var saleId: Long? = null,
    var paymenttype: Short? = null,
    var paymentDetailId :Long? = null,
    var paymoney: Int? = 0
)