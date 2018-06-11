package ecommerce.databean.order

import ecommerce.databean.basical.ProductInfo


data class OrderInfoVo(
        var customerId: Long?,
        var sequenceNo: String?,
//        var shopInfo: ShopInfo?,
        var productList: List<ProductInfo>?,
        var specialDiscount: List<Pair<String,Int>>?//first:productid,second是价格
)