package ecommerce.repository.master

import ecommerce.entity.master.Product
import org.springframework.data.jpa.repository.JpaRepository

interface IProductRepository: JpaRepository<Product,Long> {

}