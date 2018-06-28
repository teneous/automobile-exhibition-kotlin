package ecommerce.transaction.repository

import ecommerce.transaction.entity.OrderProduct
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * Created by syoka on 2018/3/28.
 */
interface IOrderProductRepository : JpaRepository<OrderProduct,Long> {
    fun findBySequenceNo(sequenceNo: String): List<OrderProduct>
}