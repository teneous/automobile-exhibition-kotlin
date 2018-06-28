package ecommerce.transaction.repository

import ecommerce.transaction.entity.PaymentDetail
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Created by syoka on 2018/3/28.
 */
interface IPaymentDetailRepository :JpaRepository<PaymentDetail,Long>{
}