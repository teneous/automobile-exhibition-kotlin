package ecommerce.transaction.databean

/**
 * 订单商品
 */
data class TrProductInfo(
        var productId: String? = null,
        var number: Int = 0,
        var marketPrice: Int = 0,
        var canDiscount: Short? = 0,
        var discountValue: Int = 0,
        var discount: Int = 0
)