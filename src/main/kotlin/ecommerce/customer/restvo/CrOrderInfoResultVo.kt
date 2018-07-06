package ecommerce.customer.restvo

import ecommerce.common.ShopInfo

/**
 * datatable订单基本vo
 * Created by syoka on 2018/6/25.
 */
data class CrOrderInfoResultVo(
        var sequence_no:String? = null,
        var shop_info: ShopInfo? = null,
        /*订单类型*/
        var order_type:String? =null,
        var pay_money:Int? = null,
        var pay_type:Short? = null,
        var order_time:String ? =null
)
