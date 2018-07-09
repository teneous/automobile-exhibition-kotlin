package ecommerce.customer.restvo


/**
 * 订单明细
 * Created by syoka on 2018/7/6.
 */
data class CrOrderDetailInfoResultVo(

        /*订单信息*/
        var order_info: CrOrderDetailBaseInfoVo?=null,
        /*顾客信息*/
        var customer_info:CrOrderDetailCustomerInfoVo?=null,
        /*商品信息*/
        var product_info:List<CrOrderDetailProductInfoVo>?=null

)