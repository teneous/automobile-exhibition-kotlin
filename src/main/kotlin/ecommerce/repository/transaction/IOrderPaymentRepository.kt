package ecommerce.repository.transaction

import ecommerce.entity.transaction.OrderPayment
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Created by syoka on 2018/3/28.
 */
interface IOrderPaymentRepository : JpaRepository<OrderPayment,Long> {
}