package ecommerce.common.enums.status

import lombok.Getter

/**
 * Created by syoka on 2018/7/9.
 */
@Getter
enum class OrderStatusEnum(val code:Short){
    NEW(1),
    CANCEL(2),
    FINISH(0),
}