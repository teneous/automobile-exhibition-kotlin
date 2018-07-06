package ecommerce.transaction.repository

import ecommerce.customer.databean.CrProductDto
import ecommerce.transaction.entity.OrderProduct
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

/**
 * Created by syoka on 2018/3/28.
 */
interface IOrderProductRepository : JpaRepository<OrderProduct,Long> {

    fun findBySequenceNo(sequenceNo: String): List<OrderProduct>

    @Query(value = "SELECT new ecommerce.customer.databean.CrProductDto(os.sequenceNo as sequenceNo,op.productName as product_name"+
             " ,p.productImg as product_img) FROM OrderSheet os, OrderProduct op, Product p WHERE os.sequenceNo = op.sequenceNo AND"+
             " op.product_id = p.product_id AND os.customer_id =:customer_id",nativeQuery = false)
    fun findCustomerOrderProduct(@Param("customer_id") customerId: Long):List<CrProductDto>
}