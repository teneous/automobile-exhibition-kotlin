package ecommerce.transaction.databean

/**
 * 下订单顾客必须信息
 * Created by syoka on 2018/6/22.
 */
data class TrCustomerInfoVo(
        /*顾客卡号*/
        val customer_identity:String,
        /*顾客名字*/
        val customer_name:String,
        /*收货地址*/
        var recevie_address:String,
        /*电话号码*/
        var mobile_no:String,
        /*是否短信提醒*/
        var is_remind:Short
)