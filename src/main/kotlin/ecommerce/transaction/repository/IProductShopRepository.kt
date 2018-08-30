package ecommerce.transaction.repository

import ecommerce.transaction.entity.ProductShop
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Created by syoka on 2018/8/30.
 */
interface IProductShopRepository:JpaRepository<ProductShop,Long> {

    fun findByShopCodeAndStatus(shopCode:String,status:Short): List<ProductShop>
}