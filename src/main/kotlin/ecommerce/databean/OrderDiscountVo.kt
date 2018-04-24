package ecommerce.databean

/**
 * Created by syoka on 2018/4/24.
 */
data class OrderDiscountVo(
    var discountType: Short,
    var doscountValue: Int,
    var specialProduct: List<Pair<String,Int>>?
)