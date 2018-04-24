package ecommerce.common

/**
 * Created by syoka on 2018/3/30.
 */

internal const val VALID: Short = 1 /*有效*/
internal const val EXPIRED: Short = 4 /*过效*/
internal const val LOGOFF: Short = 2 /*无效*/


internal const val UNPAY: Short = 0 /*尚未支付*/


//折扣相关
internal const val DISCOUNT_RATE: Short = 0 /*按照折扣率来打折  0~100*/
internal const val DISCOUNT_MONEY: Short = 1 /*按照金额来打折*/