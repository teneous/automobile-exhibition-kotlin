package ecommerce.module.master.entity

import java.time.LocalDateTime
import javax.persistence.*

/**
 * Created by syoka on 2018/3/28.
 */
@Entity
data class Payment(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id:Long? = null,
        @Column(name = "payment_type")
        var paymentType:Short? = null,
        @Column(name = "payment_name")
        var paymentName:String? = null,
        @Column(name = "index")
        var index:Short? = null,
        @Column(name = "status")
        var status:Short? = null,
        @Column(name = "start_time")
        var startTime:LocalDateTime? = null
)