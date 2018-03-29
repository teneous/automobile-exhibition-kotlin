package ecommerce.entity.transaction

import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class Sale(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @Column(name = "sequence_no")
    var sequenceNo: String? = null,
    @Column(name = "sale_times")
    var saleTimes: LocalDateTime? = null,
    @Column(name = "product_id")
    var productId: Long? = null,
    @Column(name = "money")
    var money: Int? = 0,
    @Column(name = "number")
    var number: Int? = 0
)