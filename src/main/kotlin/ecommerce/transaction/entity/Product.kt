package ecommerce.transaction.entity

import java.math.BigDecimal
import javax.persistence.*

@Entity
data class Product(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @Column(name="product_name")
    var productName: String? = null,
    @Column(name = "product_id")
    var productId:String? =null,
    @Column(name = "product_type")
    var productType: Short? =null,
    @Column(name = "price")
    var price: BigDecimal? =null,
    @Column(name = "product_img")
    var productImg:String? = null,
    @Column(name="status")
    var status: Short? = null
)