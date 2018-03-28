package ecommerce.databean

data class ProductInfo(
        var productId: Long? = null,
        var number: Int? = 0,
        var prePrice: Int? = 0,
        var discount: Int? = 0
)