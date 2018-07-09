package ecommerce.transaction.extendfunc

import ecommerce.transaction.databean.TrProductInfo
import java.math.BigDecimal


/**
 * Created by syoka on 2018/4/24.
 */
fun Int.abs(): Int {
    return Math.abs(this)
}

operator fun Int.times(number:Int):BigDecimal{
    return BigDecimal(number)
}

operator fun Int.times(number:BigDecimal):BigDecimal{
    return number
}


//operator fun TrProductInfo.times(number: Int): Int {
//    return number * market_price
//}