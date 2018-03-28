package ecommerce.repository.customer

import ecommerce.entity.customer.CustomerProduct
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Created by syoka on 2018/3/28.
 */
interface ICustomerProductRepository: JpaRepository<CustomerProduct,Long> {
}