package ecommerce.transaction.repository

import ecommerce.transaction.entity.Product
import org.springframework.data.jpa.repository.JpaRepository

interface IProductRepository: JpaRepository<Product,Long> {

}