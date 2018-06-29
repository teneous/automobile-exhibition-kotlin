package ecommerce.customer.service.impl

import ecommerce.common.databean.ShopInfo
import ecommerce.customer.restvo.CrOrderInfo
import ecommerce.transaction.databean.TrCrOrderCondition
import ecommerce.customer.repository.ICustomerRepository
import ecommerce.customer.restvo.CrBasicalVo
import ecommerce.transaction.repository.IOrderSheetRepository
import ecommerce.customer.service.ICrGetCustomerHitoryOrderService
import ecommerce.transaction.repository.IOrderProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class CrGetCustomerHitoryOrderServiceImpl: ICrGetCustomerHitoryOrderService {

    @Autowired
    private lateinit var customerRepository: ICustomerRepository
    @Autowired
    private lateinit var orderSheetRepository: IOrderSheetRepository
    @Autowired
    private lateinit var orderProductRepository: IOrderProductRepository

    override fun getAllCustomerHistoryOrder(customerId: Long,pageable: Pageable):List<CrOrderInfo>  {
        val resultList = ArrayList<CrOrderInfo>()
        val customer = customerRepository.findById(customerId)
        if(!customer.isPresent) return emptyList()
        val basicalList = orderSheetRepository.listCrOrderBasicalDto(customerId,pageable)
//        val baseInfo = basicalList.groupBy { it.sequenceNo }
        val orderProductMap = orderProductRepository.findCustomerOrderProduct(customerId).groupBy { it.sequenceNo }

        basicalList.forEach{
            CrOrderInfo().apply {
                this.sequence_no = it.sequenceNo
                this.shop_info = ShopInfo().apply {
                    shopCode = it.shopCode
                    shopName = it.shopName
                }
                val productList = ArrayList<CrBasicalVo>()
                this.product_basical_list = orderProductMap[it.sequenceNo]?.forEach {
                    val basicalVo = CrBasicalVo().apply {
                        product_name = it.productName
                        product_img = it.productImg
                    }
                    productList.add(basicalVo)
                }.let { productList }
                this.pay_money = it.payMoney
                this.pay_type = it.status
            }.let {resultList.add(it)}
        }
        return resultList
    }

    override fun getCustomerHistoryOrder(customerId: Long, searchCondition: TrCrOrderCondition) {

    }
}