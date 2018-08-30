package ecommerce.transaction.databean


/**
 * 下单时请求订单vo
 */
data class TrPlaceOrderInfoVo(
        /*顾客信息*/
        var customer_info: TrCustomerInfoVo,
        /*店铺信息*/
        var shop_code:String,
        /*商品列表*/
        var product_list: List<TrProductInfo>,
        /*特殊打折卷*/
        var special_discount: List<Pair<String,Int>>?//first:特殊商品,second是价格
)