package ecommerce.repository.setting

import ecommerce.entity.setting.Payment
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Created by syoka on 2018/3/28.
 */
interface IPaymentRepository: JpaRepository<Payment,Long> {
}