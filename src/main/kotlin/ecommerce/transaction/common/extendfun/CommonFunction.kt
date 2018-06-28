package ecommerce.transaction.common.extendfun

import ecommerce.transaction.databean.TrProductInfo


/**
 * Created by syoka on 2018/4/24.
 */
fun Int.abs(): Int {
    return Math.abs(this)
}

operator fun TrProductInfo.times(number: Int): Int {
    return number * marketPrice
}