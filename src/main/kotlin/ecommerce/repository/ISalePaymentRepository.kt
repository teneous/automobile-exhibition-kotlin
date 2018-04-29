package ecommerce.repository

import ecommerce.entity.SalePayment
import org.springframework.data.jpa.repository.JpaRepository

interface ISalePaymentRepository: JpaRepository<SalePayment, Long> {

}