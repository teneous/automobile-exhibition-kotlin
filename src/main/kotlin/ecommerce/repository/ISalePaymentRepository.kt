package ecommerce.repository

import ecommerce.entity.DealsPayment
import org.springframework.data.jpa.repository.JpaRepository

interface IDealsPaymentRepository: JpaRepository<DealsPayment, Long> {

}