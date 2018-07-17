package ecommerce.transaction.databean

/**
 * 订单主金种
 */
data class TrPaymentVo(
        var sequenceNo: String,
        var paymentDetail: List<TrPaymentDetailVo>
//        var redPacket: List<Pair<Short,Int>>
)
