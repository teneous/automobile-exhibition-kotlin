package ecommerce.transaction.entity

import javax.persistence.*

@Entity
data class Product(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,
    @Column(name="product_name")
    var productName: String? = "",
    @Column(name = "product_type")
    var productType: Short? = 0,
    @Column(name="status")
    var status: Short? = 0
)