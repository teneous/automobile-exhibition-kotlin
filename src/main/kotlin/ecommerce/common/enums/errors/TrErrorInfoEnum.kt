package ecommerce.common.enums.errors

import lombok.Getter


/**
 * Created by syoka on 2018/4/24.
 */
@Getter
enum class TrErrorInfoEnum(var code: Int?, var errormsg: String?) {

    ORDER_HAS_BEEN_EXPIRED(100,"your order has been exipred please reorder to complete your shopping"),
    ORDER_ACTUALPAY_NOT_EQUAL_TO_REAL_PAY(200,"acutal pay is not equals to your order"),


    //订单相关
    ORDER_OUT_OF_DATE(3001,"订单已经过期"),
    ORDER_PRODUCT_LIST_IS_EMPTY(3101,"订单商品是空的"),
    ORDER_ACTUALPAY_NOT_EQUAL_ORDERMONEY(3201,"订单金额不等于支付金额")

}