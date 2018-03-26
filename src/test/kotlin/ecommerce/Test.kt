package ecommerce

import ecommerce.transaction.repository.ProductRepository
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired

class Test{

    @Autowired
    lateinit var productRepository: ProductRepository

    @Test
    @Throws(Exception::class)
    fun Product(){
        val findAll = productRepository.findAll()
        findAll.forEach{
            println(it)
        }
    }
}