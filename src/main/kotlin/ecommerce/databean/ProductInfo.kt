package ecommerce.databean

data class ProductInfo(
        var productId: String? = null,
        var number: Int = 0,
        var marketPrice: Int = 0,
        var discount: Int = 0,
        var canDiscount: Short? =0
)