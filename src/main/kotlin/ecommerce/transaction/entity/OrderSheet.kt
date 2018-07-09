package ecommerce.transaction.entity

import java.math.BigDecimal
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
    @Column(name = "seller_code")
    var sellerCode: String? = null,
    @Column(name ="order_type")
    var orderType :Short? = null,
    @Column(name = "seller_shop_name")
    var sellerShopName: String? = null,
    @Column(name = "shop_code")
    var shopCode: String? = null,
    @Column(name = "order_time")
    var orderTime: LocalDateTime? = null,
    @Column(name = "pay_time")
    var payTime: LocalDateTime?=null,
    @Column(name ="express")
    var express:Short?=null,
    @Column(name = "status")
    var status: Short? = null,
    @Column(name = "total_money")
    var totalMoney: BigDecimal ? = null,
    @Column(name = "total_discount")
    var totalDiscount: BigDecimal? = null,
    @Column(name="pay_money")
    var payMoney:BigDecimal? = null,
    @Column(name ="carton_no")
    var cartonNo: String? = null,
    @Column(name ="recevie_address")
    var recevieAddress:String?=null,
    @Column(name ="mobile_no")
    var mobileNo:String?=null
)