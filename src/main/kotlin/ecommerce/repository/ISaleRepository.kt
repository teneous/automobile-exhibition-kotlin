package ecommerce.repository

import ecommerce.entity.Sale
import org.springframework.data.jpa.repository.JpaRepository

interface ISaleRepository : JpaRepository<Sale,Long> {

}