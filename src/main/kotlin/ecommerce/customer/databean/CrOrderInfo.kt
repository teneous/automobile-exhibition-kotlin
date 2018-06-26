package ecommerce.customer.databean

import ecommerce.common.databean.ShopInfo

/**
 * Created by syoka on 2018/6/25.
 */
data class CrOrderInfo(
    var sequenceNo:String? = null,
    var shopInfo: ShopInfo? = null,
    var productBasicalList:List<Pair<String,String>>? = null,
    val paymoney:Int? = null,
    var paytype:Short? = null

)
