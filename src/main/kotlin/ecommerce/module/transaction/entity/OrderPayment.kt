package ecommerce.module.transaction.entity

import java.time.LocalDateTime
import javax.persistence.*

/**
 * Created by syoka on 2018/3/28.
 */
@Entity
data class OrderPayment(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @Column(name = "sequence_no")
    var sequenceNo: String? = null,
    var paymentType: Short? = null,
    @Column(name = "payment_detail")
    var paymentDetail: Long? = null,
    @Column(name = "purchase_time")
    var purchaseTime: LocalDateTime? = null,
    @Column(name = "payment_money")
    var paymentMoney: Int? = null
)