package ecommerce.transaction.service

import ecommerce.common.SYOKA
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * 生成订单号序列码
 * Created by syoka on 2018/6/22.
 */
interface ITrGenerateSequenceNoService{

    companion object {
        val sequenceFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    }

    /**
     * 创建订单号码
     */
    fun generateSequenceNo():String

    /**
     * 创建商家码
    */
    fun generateSellerSequenceNo(shopcode:String): String {
        return "${SYOKA}${sequenceFormatter.format(LocalDateTime.now())}$shopcode"
    }
}