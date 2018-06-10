package ecommerce.entity

import java.time.LocalDateTime
import javax.persistence.*

/**
 * Created by syoka on 2018/3/28.
 */
@Entity
data class OrderSheet(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @Column(name = "customer_id")
    var customerId: Long? = null,
    @Column(name = "sequence_no")
    var sequenceNo: String? = null,
    @Column(name = "order_time")
    var orderTime: LocalDateTime? = null,
    @Column(name = "status")
    var status: Short? = null,
    @Column(name = "total_money")
    var totalMoney: Int ? = null,
    @Column(name = "total_discount")
    var totalDiscount: Int? = null,
    @Column(name ="carton_no")
    var cartonNo: String? = null
//    @Column(name="payment_type")
//    var paymentType: Short? = null,
//    @Column(name="payment_detailid")
//    var paymentDetailId :Long? =null
)