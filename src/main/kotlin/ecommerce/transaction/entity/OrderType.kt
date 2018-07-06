package ecommerce.transaction.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

/**
 * Created by syoka on 2018/7/6.
 */
@Entity
data class OrderType(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id:Long,
        var type:Short,
        var name:String,
        var status:Short
)