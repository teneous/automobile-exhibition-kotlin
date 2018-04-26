package ecommerce.databean


data class OrderInfoVo(
    var customerId: Long?,
    var sequenceNo: String?,
    var productList: List<ProductInfo>?,
    var specialDiscount: List<Pair<String,Int>>?//first:productid,second是价格
)