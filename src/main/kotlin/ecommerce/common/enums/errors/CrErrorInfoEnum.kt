package ecommerce.common.enums.errors

import lombok.Getter

/**
 * Created by syoka on 2018/7/9.
 */
@Getter
enum class CrErrorInfoEnum (var code: Int?, var errormsg: String?) {

    CUSTOMER_NOT_EXIST(100,"customer info errors"),
    CUSTOMER_STATU_INVALID(101,"当前顾客不是有效顾客"),

}