package ecommerce.transaction.common

/**
 * Created by syoka on 2018/4/24.
 */
enum class ErrorInfo(name:String){
    ORDER_HAS_BENN_EXPIRED("your order has been exipred please reorder to complete your shopping"),
    ORDER_ACTUALPAY_NOT_EQUAL_TO_REAL_PAY("acutal pay is not equals to your order"),


}