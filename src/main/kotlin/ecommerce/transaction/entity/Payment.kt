package ecommerce.transaction.entity

import java.time.LocalDateTime
import javax.persistence.*

/**
 * Created by syoka on 2018/3/28.
 */
@Entity
data class Payment(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id:Long,
        @Column(name = "payment_type")
        var paymentType:Short,
        @Column(name = "payment_name")
        var paymentName:String,
        @Column(name = "order_no")
        var orderNo:Short,
        @Column(name = "status")
        var status:Short,
        @Column(name = "start_time")
        var startTime:LocalDateTime
)