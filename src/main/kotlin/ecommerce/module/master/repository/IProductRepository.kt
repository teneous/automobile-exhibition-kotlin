package ecommerce.module.master.repository

import ecommerce.module.master.entity.Product
import org.springframework.data.jpa.repository.JpaRepository

interface IProductRepository: JpaRepository<Product,Long> {

}