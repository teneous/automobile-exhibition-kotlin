package ecommerce.databean


data class PaymentVo(
    var sequenceNo: String,
    var totalPayment: Int,
    var paymentType: Short,
    var paymentDetail: Long,
    var redPacket: List<Pair<Short,Int>>


)
