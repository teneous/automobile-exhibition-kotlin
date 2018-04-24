package ecommerce.common.extendfun

import ecommerce.databean.ProductInfo
import ecommerce.entity.customer.OrderProduct


/**
 * Created by syoka on 2018/4/24.
 */
fun Int.abs(): Int {
    return Math.abs(this)
}

//operator fun Int.times(discount: OrderProduct?): Int? {
//    return discount?.totalNum?.times(discount.prePrice!!)
//}

operator fun ProductInfo.times(number: Int): Int {
    return number * marketPrice
}