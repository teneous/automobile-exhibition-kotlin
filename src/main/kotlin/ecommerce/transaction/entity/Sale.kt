package ecommerce.transaction.entity

import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class Sale(
    @Id @GeneratedValue
    val id: Long = 0,
    var customerid: Long,
    var sequence_no: String? = null,
    var sale_times: LocalDateTime? = null,
    var realpay_money: Int = 0,
    var totalmoney: Int = 0,
    var discount: Int = 0,
    var discount_type: Long = 0L,
    var re_sequence_no: String? = null
)