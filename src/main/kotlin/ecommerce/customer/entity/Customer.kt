package ecommerce.customer.entity

import java.time.LocalDateTime
import javax.persistence.*

/**
 * Created by syoka on 2018/3/28.
 */
@Entity
data class Customer(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,
        @Column(name = "identity_no")
        var identityNo: String? = null,
        @Column(name = "nick_name")
        var nickName: String? = null,
        @Column(name = "register_time")
        var registerTime: LocalDateTime? = null,
        @Column(name = "status")
        var status: Short? = null,
        @Column(name = "point")
        var point:Int? = null
)