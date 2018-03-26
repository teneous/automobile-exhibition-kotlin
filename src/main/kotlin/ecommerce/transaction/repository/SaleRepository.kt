package ecommerce.transaction.repository

import ecommerce.transaction.entity.Sale
import org.springframework.data.jpa.repository.JpaRepository

internal interface SaleRepository: JpaRepository<Sale,Long> {

}