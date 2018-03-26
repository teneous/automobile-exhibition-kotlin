package ecommerce.transaction.repository

import ecommerce.transaction.entity.Product
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository: JpaRepository<Product,Long> {

}