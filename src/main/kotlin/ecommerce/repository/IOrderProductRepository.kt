package ecommerce.repository

import ecommerce.entity.OrderProduct
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * Created by syoka on 2018/3/28.
 */
@Repository
interface IOrderProductRepository : JpaRepository<OrderProduct,Long> {
    fun findBySequenceNo(sequenceNo: String): List<OrderProduct>
}