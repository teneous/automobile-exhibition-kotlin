package ecommerce.transaction.entity

import javax.persistence.*

/**
 * Created by syoka on 2018/8/30.
 */
@Entity
data class ProductShop(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,
        @Column(name="shop_code")
        var shopCode: String? = null,
        @Column(name="product_code")
        var productCode: String? = null,
        @Column(name="can_discount")
        var canDiscount: Short? = null,
        @Column(name="status")
        var status: Short? = null
)