package ecommerce.service.impl

import ecommerce.common.UNPAY
import ecommerce.common.VALID
import ecommerce.databean.OrderInfo
import ecommerce.entity.customer.OrderProduct
import ecommerce.entity.master.Product
import ecommerce.entity.transaction.Order
import ecommerce.repository.customer.ICustomerRepository
import ecommerce.repository.customer.IOrderProductRepository
import ecommerce.repository.transaction.IOrderRepository
import ecommerce.service.IPlaceOrderService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class PlaceOrderServiceImpl: IPlaceOrderService{

    @Autowired
    lateinit var customerRepository: ICustomerRepository
    @Autowired
    lateinit var productRepository: IOrderProductRepository
    @Autowired
    lateinit var orderRepository: IOrderRepository

    override fun placeOrder(orderInfo: OrderInfo) {
        if(!checkRequestData(orderInfo)) return
        val now = LocalDateTime.now()
        //vo->entity,对象之间的转换应该用map更为合适
        orderInfo.productList?.map { OrderProduct(
            sequenceNo = orderInfo.sequenceNo,
            customerId = orderInfo.customerId,
            totalNum = it.number,
            productId = it.productId
        )}.let { productRepository.saveAll(it?: listOf()) }

        //保存订单信息
        Order().apply {
            orderTime = now
            status = UNPAY
            sequenceNo = orderInfo.sequenceNo
            customerId = orderInfo.customerId
        }.let { orderRepository.save(it)}
    }
    /**
     * 数据校验
     */
    fun checkRequestData(orderInfo: OrderInfo): Boolean{
        val customer = customerRepository.getOne(orderInfo.customerId ?: return false)
        if(VALID != customer.status) return false
        if (orderInfo.productList?.size == 0) return false
        return true
    }
}

fun buildOrderProduct(build: OrderProduct.()->Unit):OrderProduct{
    val product = OrderProduct()
    build.invoke(product)
    return product
}