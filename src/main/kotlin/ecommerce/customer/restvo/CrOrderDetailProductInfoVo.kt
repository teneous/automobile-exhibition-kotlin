package ecommerce.customer.restvo

/**
 * Created by syoka on 2018/7/6.
 */
data class CrOrderDetailProductInfoVo(
        /*商品code*/
        var product_code:String,
        /*商品名字*/
        var product_name:String,
        /*商品图片*/
        var product_img:String,
        /*商品描述*/
        var product_desc:String
)