package ecommerce.common.enums.errors

/**
 * Created by syoka on 2018/7/6.
 */
enum class TransactionType( var type: Short,var markname: String) {

    OTHER(0, "其他"),
    DAY_LIFE(1, "日常"),
    TRANSPORTATION(2, "交通"),
    RESTURANT(3, "餐饮");

    companion object {
        fun getName(type:Short):String{
            for (value in values()) {
                if (value.type.equals(type)) {
                    return value.markname
                }
            }
            return ""
        }
    }
}