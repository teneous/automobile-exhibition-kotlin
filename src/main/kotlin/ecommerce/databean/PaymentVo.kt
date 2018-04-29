package ecommerce.databean


data class PaymentVo(
    var sequenceNo: String,
    var paymentDetail: List<PaymentDetailVo>,
    var redPacket: List<Pair<Short,Int>>


)
