package ecommerce

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

//@ComponentScan(value = ["ecommerce.resource"])
//@EnableAutoConfiguration
@SpringBootApplication
class EcommerceApplication

fun main(args: Array<String>) {
    runApplication<EcommerceApplication>(*args)
}
