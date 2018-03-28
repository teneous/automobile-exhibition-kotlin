package ecommerce.databean

data class OrderInfo(
    var productList: ProductInfo? = null,
    var customerId: Long? = null
)