package ecommerce.repository

import ecommerce.entity.Customer
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.domain.Specification



/**
 * Created by syoka on 2018/3/28.
 */
interface ICustomerRepository: JpaRepository<Customer,Long>{

//    fun findAll(spec: Specification<T>, pageable: Pageable): Page<T>

}