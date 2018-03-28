package ecommerce.repository.master

import ecommerce.entity.master.PaymentDetail
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Created by syoka on 2018/3/28.
 */
interface IPaymentDetailRepository :JpaRepository<PaymentDetail,Long>{
}