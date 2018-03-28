package ecommerce.repository.master

import ecommerce.entity.master.Payment
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Created by syoka on 2018/3/28.
 */
interface IPaymentRepository: JpaRepository<Payment,Long> {
}