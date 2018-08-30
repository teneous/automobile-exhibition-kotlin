package ecommerce.transaction.databean

import java.math.BigDecimal

/**
 * 订单商品
 */
data class TrProductInfo(
        var product_id: String,
        var number: Int = 0,
        var discount_value: BigDecimal? = BigDecimal.ZERO,
        //折扣类型
        var discount: Int = 0
)