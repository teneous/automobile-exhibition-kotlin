package ecommerce.transaction.databean

/**
 * Created by syoka on 2018/6/22.
 */
data class TrCustomerInfoVo(
        val customerId:Long,
        /*收货地址*/
        var recevieAddress:String,
        /*电话号码*/
        var mobileNo:String,
        /*是否短信提醒*/
        var isRemind:Short
)