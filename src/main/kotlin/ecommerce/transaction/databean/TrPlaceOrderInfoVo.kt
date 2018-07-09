package ecommerce.transaction.databean


/**
 * 下单时请求订单vo
 */
data class TrPlaceOrderInfoVo(
        var customer_info: TrCustomerInfoVo,
        var shop_code:String,
        var product_list: List<TrProductInfo>,
        var special_discount: List<Pair<String,Int>>?//first:特殊商品,second是价格
)