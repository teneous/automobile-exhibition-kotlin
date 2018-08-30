package ecommerce.customer.restvo

import java.math.BigDecimal

/**
 * Created by syoka on 2018/7/6.
 */
class CrOrderDetailBaseInfoVo{

        /*订单码*/
        var sequence_no:String? = null
        /*商家码*/
        var seller_code:String? = null
        /*订单类型*/
        var order_type:String? = null
        /*商家名字*/
        var shop_name:String? = null
        /*实际付款*/
        var pay_money: BigDecimal? = null
        /*下单时间*/
        var order_time:String? = null
        /*支付时间*/
        var pay_time:String? = null
        /*配送方式*/
        var express:String? = null

}



