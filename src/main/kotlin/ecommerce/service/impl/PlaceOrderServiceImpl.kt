package ecommerce.service.impl

import ecommerce.common.EXPIRED
import ecommerce.common.UNPAY
import ecommerce.common.VALID
import ecommerce.databean.OrderInfo
import ecommerce.databean.OrderInfoVo
import ecommerce.databean.PaymentVo
import ecommerce.entity.customer.OrderProduct
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
    lateinit var orderProductRepository: IOrderProductRepository
    @Autowired
    lateinit var orderRepository: IOrderRepository

    override fun placeOrder(orderInfo: OrderInfoVo) {
        if(!checkPlaceOrder(orderInfo)) return
        val now = LocalDateTime.now()

        //保存订单信息
        Order().apply {
                orderTime = now
                status = UNPAY
                sequenceNo = orderInfo.sequenceNo
                customerId = orderInfo.customerId
        }.let { orderRepository.save(it)}

        //保存订单商品表{vo->entity,对象之间的转换应该用map更为合适}
        orderInfo.productList?.map { OrderProduct(
                sequenceNo = orderInfo.sequenceNo,
                customerId = orderInfo.customerId,
                totalNum = it.number,
                productId = it.productId

        )}.let { orderProductRepository.saveAll(it?: listOf()) }
    }

    override fun payForOrder(paymentVo: PaymentVo) {
        val currentOrder = orderRepository.findBySequenceNo(paymentVo.sequenceNo)

        if ((EXPIRED == currentOrder.paymentType)) {
            print("order has been cancel!!!")
        }
        //金钱校验

    }



    /**
     * 下订单数据校验
     */
    fun checkPlaceOrder(orderInfo: OrderInfoVo): Boolean{
        val customer = customerRepository.getOne(orderInfo.customerId ?: return false)
        if(VALID != customer.status) return false
        if (orderInfo.productList?.size == 0) return false
        return true
    }

    fun checkPayForOrder(paymentVo: PaymentVo){
        val productList = orderProductRepository.findBySequenceNo(paymentVo.sequenceNo)
        productList.fold(productList,)
    }
}

