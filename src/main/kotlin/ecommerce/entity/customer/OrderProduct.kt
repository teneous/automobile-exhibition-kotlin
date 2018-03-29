package ecommerce.entity.customer

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
    var productId: Long? = null,
    @Column(name="sequence_no")
    var sequenceNo: String? = null,
    @Column(name="validate_time")
    var validateTime: LocalDateTime? = null,
    @Column(name="total_num")
    var totalNum: Int? = null,
    @Column(name="status")
    var status:Short? = null,
    @Column(name="pre_price")
    var prePrice:Int? = null,
    @Column(name = "sale_id")
    var saleId: Long? = null,
    @Column(name = "resale_id")
    var reSaleId: Long? = null,
    @Column(name = "discount")
    var discount: Long? = null
)