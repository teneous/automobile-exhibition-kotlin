package ecommerce.repository.transaction

import ecommerce.entity.transaction.Sale
import org.springframework.data.jpa.repository.JpaRepository

internal interface ISaleRepository : JpaRepository<Sale,Long> {

}