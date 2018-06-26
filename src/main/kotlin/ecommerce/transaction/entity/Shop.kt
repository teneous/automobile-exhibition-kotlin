package ecommerce.transaction.entity

import javax.persistence.*

/**
 * Created by syoka on 2018/6/26.
 */
@Entity
data class Shop(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,
        @Column(name = "name")
        var name :String? = null,
        @Column(name = "code")
        var code: String? = null
)