package ecommerce.common.enums.status


/**
 * Created by syoka on 2018/7/9.
 */
@lombok.Getter
enum class OrderStatusEnum(val code:Short){
    EXPIRE(-1),
    NEW(1),
    CANCEL(2),
    WAIT(3),
    FINISH(0)
}