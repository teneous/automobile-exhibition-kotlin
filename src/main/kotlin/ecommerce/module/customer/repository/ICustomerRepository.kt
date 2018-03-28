package ecommerce.module.customer.repository

import ecommerce.module.customer.entity.Customer
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Created by syoka on 2018/3/28.
 */
interface ICustomerRepository: JpaRepository<Customer,Long>