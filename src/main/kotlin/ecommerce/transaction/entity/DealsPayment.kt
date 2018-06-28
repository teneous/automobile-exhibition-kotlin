package ecommerce.transaction.entity

import javax.persistence.*

@Entity
data class DealsPayment(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id :Long? = null,
    @Column(name = "deals_id")
    var saleId: Long? = null,
    @Column(name = "payment_type")
    var paymentType: Short? = null,
    @Column(name = "payment_detail_id")
    var paymentDetailId :Long? = null,
    @Column(name = "paymoney")
    var payMoney: Int? = 0
)