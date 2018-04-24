package ecommerce.databean

//import com.sun.xml.internal.ws.developer.Serialization

//@Serialization
data class OrderInfoVo(
    var sequenceNo: String?,
    var productList: List<ProductInfo>?,
    var customerId: Long?,
    var discountVo: OrderDiscountVo?
)