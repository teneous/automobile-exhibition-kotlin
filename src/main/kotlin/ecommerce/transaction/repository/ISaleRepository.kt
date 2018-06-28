package ecommerce.transaction.repository

import ecommerce.transaction.entity.Sale
import org.springframework.data.jpa.repository.JpaRepository

interface ISaleRepository : JpaRepository<Sale,Long> {

}