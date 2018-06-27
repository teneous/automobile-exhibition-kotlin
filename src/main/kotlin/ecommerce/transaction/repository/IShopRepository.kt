package ecommerce.transaction.repository

import ecommerce.transaction.entity.Shop
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Created by syoka on 2018/6/27.
 */
interface IShopRepository: JpaRepository<Shop, Long> {}