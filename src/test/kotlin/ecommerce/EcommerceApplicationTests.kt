package ecommerce

import ecommerce.transaction.repository.IOrderSheetRepository
import ecommerce.transaction.repository.IShopRepository
import ecommerce.transaction.service.ITrGenerateSequenceNoService
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import java.util.*

@RunWith(SpringRunner::class)
@SpringBootTest
class EcommerceApplicationTests{

    @Autowired
    lateinit var nos : ITrGenerateSequenceNoService
    @Autowired
    lateinit var orders : IOrderSheetRepository
    @Autowired
    lateinit var shop : IShopRepository

    @Test
    fun m91(){
        val findAll = orders.findAll()
        val random = Random(47)
        for (orderSheet in findAll) {
            val generateSequenceNo = nos.generateSequenceNo()
            orderSheet.sequenceNo = generateSequenceNo
            val shop = shop.findById(random.nextInt(7).toLong())
            if (shop.isPresent) {
                orderSheet.shopCode =shop.get().code
                orderSheet.sellerShopName =shop.get().name
            }
            orders.save(orderSheet)
        }
    }
}


