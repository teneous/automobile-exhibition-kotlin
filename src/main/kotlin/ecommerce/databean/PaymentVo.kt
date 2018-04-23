package ecommerce.databean

data class PaymentVo(
    var sequenceNo: String,
    var totalPayment: Int,
    var paymentType: Short,
    var redPacket: List<SpecialPaymentVo>


)
