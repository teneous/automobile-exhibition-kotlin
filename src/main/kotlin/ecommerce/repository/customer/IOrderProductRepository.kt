package ecommerce.repository.customer

import ecommerce.entity.customer.OrderProduct
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Created by syoka on 2018/3/28.
 */
interface IOrderProductRepository : JpaRepository<OrderProduct,Long> {
    fun findBySequenceNo(sequenceNo: String): List<OrderProduct>
}