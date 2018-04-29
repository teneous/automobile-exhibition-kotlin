package ecommerce.repository.setting

import ecommerce.entity.Product
import org.springframework.data.jpa.repository.JpaRepository

interface IProductRepository: JpaRepository<Product,Long> {

}