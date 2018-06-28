package ecommerce.transaction.entity

import java.time.LocalDateTime
import javax.persistence.*

/**
 * Created by syoka on 2018/3/28.
 */
@Entity
data class PaymentDetail(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @Column(name = "payment_id")
    var paymentId: Long? = null,
    @Column(name = "detail_name")
    var detailName: String? = null,
    @Column(name = "order_no")
    var orderNo: Short? = null,
    @Column(name = "status")
    var status: Short? = null,
    @Column(name = "own_conpany")
    var ownConpany: String? = null,
    @Column(name = "payment_type")
    var authorizedTime: LocalDateTime? = null
)
