package ecommerce.customer.repository

import ecommerce.customer.entity.Customer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


/**
 * Created by syoka on 2018/3/28.
 */
@Repository
interface ICustomerRepository: JpaRepository<Customer,Long>{

//    fun findAll(spec: Specification<T>, pageable: Pageable): Page<T>

}