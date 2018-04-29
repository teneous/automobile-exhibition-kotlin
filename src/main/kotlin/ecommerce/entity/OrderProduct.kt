package ecommerce.entity

import ecommerce.common.VALID
import java.time.LocalDateTime
import javax.persistence.*

/**
 * Created by syoka on 2018/3/28.
 */
@Entity
data class OrderProduct(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @Column(name="customer_id")
    var customerId: Long? = null,
    @Column(name="product_id")
    var productId: String? = null,
    @Column(name="sequence_no")
    var sequenceNo: String? = null,
    @Column(name="validate_time")
    var validateTime: LocalDateTime? = null,
    @Column(name="total_num")
    var totalNum: Int? = null,
    @Column(name="status")
    var status:Short? = null,
    @Column(name="real_total_money")//折扣后总钱
    var realTotalMoney:Int? = null,
    @Column(name = "discount")
    var discount: Int? = null
){
    constructor(sequenceNo: String, customerId: Long, status: Short = VALID,
                totalNum: Int = 1, realTotalMoney: Int = 0,discount: Int?): this()
}