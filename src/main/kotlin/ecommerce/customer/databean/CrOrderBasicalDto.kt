package ecommerce.customer.databean

import java.time.LocalDateTime

/**
 * Created by syoka on 2018/6/26.
 */
data class CrOrderBasicalDto(
        var sequenceNo:String? = "",
        var shopCode:String?="",
        var shopName:String?  = "",
        var payMoney:Int? = 0,
        var orderTime :LocalDateTime?=null,
        var status:Short?=0
)
