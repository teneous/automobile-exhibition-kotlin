package ecommerce.transaction.repository

import ecommerce.transaction.entity.DealsPayment
import org.springframework.data.jpa.repository.JpaRepository

interface IDealsPaymentRepository: JpaRepository<DealsPayment, Long> {

}