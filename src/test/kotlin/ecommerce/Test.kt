package ecommerce

import ecommerce.transaction.repository.IPaymentDetailRepository
import ecommerce.transaction.repository.IProductRepository
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired

class Test: EcommerceApplicationTests(){

    @Autowired
    lateinit var productRepository: IProductRepository
    @Autowired
    lateinit var paymentDetailRepository: IPaymentDetailRepository

    @Test
    @Throws(Exception::class)
    fun Product(){
        val findAll = productRepository.findAll()
        findAll.forEach{
            println(it)
        }
    }


    @Test
    fun PaymentDetail(){
        val findAll = paymentDetailRepository.findAll()
        findAll.forEach{
            println(it)
        }
    }

}