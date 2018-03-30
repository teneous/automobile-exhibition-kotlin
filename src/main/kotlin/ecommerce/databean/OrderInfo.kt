package ecommerce.databean

import com.sun.xml.internal.ws.developer.Serialization

@Serialization
data class OrderInfo(
    var sequenceNo: String? = null,
    var productList: List<ProductInfo>? = null,
    var customerId: Long? = null
)