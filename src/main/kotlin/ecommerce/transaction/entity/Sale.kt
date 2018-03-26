package ecommerce.transaction.entity

import java.time.LocalDateTime
import javax.persistence.*

@Entity
internal data class Sale(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @Column(name = "sequence_no")
    var sequenceNo: String? = null,
    @Column(name = "sale_times")
    var saleTimes: LocalDateTime? = null,
    @Column(name = "product_id")
    var productId: Long? = null,
    @Column(name = "per_price")
    var perPrice: Int? = 0,
    @Column(name = "number")
    var number: Int? = 0,
    @Column(name = "discount")
    var discount: Int = 0,
    @Column(name = "special_opr")
    var specialOpr: String? = null
)