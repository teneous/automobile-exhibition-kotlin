package ecommerce.repository

import ecommerce.entity.OrderSheet
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

/**
 * Created by syoka on 2018/3/28.
 */
@Repository
interface IOrderSheetRepository : JpaRepository<OrderSheet,Long> {

    fun findBySequenceNo(sequenceNo: String): OrderSheet

    @Query(value = "From OrderSheet where customerId=:cid and status = 1",nativeQuery = false)
    fun findValidOrderForCustomer(@Param("cid")customerId:Long):List<OrderSheet>
}
