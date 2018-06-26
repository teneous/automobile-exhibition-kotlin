package ecommerce.customer.databean

import java.time.LocalDateTime

/**
 * Created by syoka on 2018/6/26.
 */
data class CrOrderBasicalDto(
    val sequenceNo:String?,
    val shopName:String?,
    val payMoney:Int?,
    var orderTime:LocalDateTime
)