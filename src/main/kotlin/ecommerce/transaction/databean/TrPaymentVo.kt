package ecommerce.transaction.databean


data class TrPaymentVo(
        var sequenceNo: String,
        var paymentDetail: List<TrPaymentDetailVo>,
        var redPacket: List<Pair<Short,Int>>
)
