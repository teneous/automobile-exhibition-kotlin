package ecommerce.transaction.repository

import ecommerce.customer.databean.CrOrderBasicalDto
import ecommerce.transaction.entity.OrderSheet
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

/**
 * Created by syoka on 2018/3/28.
 */
interface IOrderSheetRepository : JpaRepository<OrderSheet,Long> {


    fun findBySequenceNo(sequenceNo: String): OrderSheet

    /**
     * 获取该顾客的最近订单
     */
    @Query(value = "SELECT new ecommerce.customer.databean.CrOrderBasicalDto(os.sequenceNo as sequenceNo," +
            " sp.code as shopCode,sp.name as shopName,os.payMoney as payMoney,os.orderTime as orderTime)  " +
            " FROM OrderSheet os ,Shop sp WHERE os.shopCode = sp.code and os.customerId =?1",
            nativeQuery = false)
    fun listCrOrderBasicalDto(@Param("customerId") customerId: Long,pageable: Pageable): List<CrOrderBasicalDto>

    @Query(value = "From OrderSheet where customerId=:cid and status = 1",nativeQuery = false)
    fun findValidOrderForCustomer(@Param("cid")customerId:Long):List<OrderSheet>
}
