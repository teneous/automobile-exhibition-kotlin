package ecommerce.common.extendfun

import ecommerce.databean.basical.ProductInfo


/**
 * Created by syoka on 2018/4/24.
 */
fun Int.abs(): Int {
    return Math.abs(this)
}

operator fun ProductInfo.times(number: Int): Int {
    return number * marketPrice
}