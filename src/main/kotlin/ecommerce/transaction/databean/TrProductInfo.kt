package ecommerce.transaction.databean

/**
 * 订单商品
 */
data class TrProductInfo(
        var product_id: String? = null,
        var number: Int = 0,
        var market_price: Int = 0,
        var can_discount: Short? = 0,
        var discount_value: Int = 0,
        var discount: Int = 0
)