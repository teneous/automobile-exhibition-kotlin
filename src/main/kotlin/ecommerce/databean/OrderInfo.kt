package ecommerce.databean

import com.sun.xml.internal.ws.developer.Serialization

@Serialization
data class OrderInfo(
    var productList: ProductInfo? = null,
    var customerId: Long? = null
)