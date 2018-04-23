package ecommerce.repository.transaction

import ecommerce.entity.transaction.Order
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Created by syoka on 2018/3/28.
 */
interface IOrderRepository : JpaRepository<Order,Long> {

    fun findBySequenceNo(sequenceNo: String):Order

}
