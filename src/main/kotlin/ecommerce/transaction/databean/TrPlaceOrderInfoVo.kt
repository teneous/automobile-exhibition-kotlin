package ecommerce.transaction.databean

import ecommerce.common.ShopInfo

/**
 * 下单时请求订单vo
 */
data class TrPlaceOrderInfoVo(
        var customer_info: TrCustomerInfoVo,
        var shop_info: ShopInfo,
        var product_list: List<TrProductInfo>,
        var special_discount: List<Pair<String,Int>>?//first:productid,second是价格
)