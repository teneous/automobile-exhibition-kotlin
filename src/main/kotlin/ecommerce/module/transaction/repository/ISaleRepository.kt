package ecommerce.module.transaction.repository

import ecommerce.module.transaction.entity.Sale
import org.springframework.data.jpa.repository.JpaRepository

internal interface ISaleRepository : JpaRepository<Sale,Long> {

}