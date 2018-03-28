package ecommerce.module.transaction.repository

import ecommerce.module.transaction.entity.Order
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Created by syoka on 2018/3/28.
 */
interface IOrderRepository : JpaRepository<Order,Long>
