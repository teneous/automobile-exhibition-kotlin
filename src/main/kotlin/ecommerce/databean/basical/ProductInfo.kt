package ecommerce.databean.basical

/**
 * 订单商品
 */
data class ProductInfo(
        var productId: String? = null,
        var number: Int = 0,
        var marketPrice: Int = 0,
        var canDiscount: Short? = 0,
        var discountType: Short? = null,
        var discountValue: Int = 0,
        var discount: Int = 0
)