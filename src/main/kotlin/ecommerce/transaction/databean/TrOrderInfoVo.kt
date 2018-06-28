package ecommerce.transaction.databean

import ecommerce.common.databean.ShopInfo

data class TrOrderInfoVo(
        var customerInfo: TrCustomerInfoVo,
        var shopInfo: ShopInfo,
        var productList: List<TrProductInfo>,
        var specialDiscount: List<Pair<String,Int>>?//first:productid,second是价格
)