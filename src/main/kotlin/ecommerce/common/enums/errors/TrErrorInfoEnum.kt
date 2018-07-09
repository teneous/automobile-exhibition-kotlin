package ecommerce.common.enums.errors

import lombok.Getter


/**
 * Created by syoka on 2018/4/24.
 */
@Getter
enum class TrErrorInfoEnum(var code: Int?, var errormsg: String?) {

    ORDER_HAS_BEEN_EXPIRED(100,"your order has been exipred please reorder to complete your shopping"),
    ORDER_ACTUALPAY_NOT_EQUAL_TO_REAL_PAY(200,"acutal pay is not equals to your order"),
    ORDER_PRODUCT_LIST_IS_EMPTY(301,"订单商品是空的")
}