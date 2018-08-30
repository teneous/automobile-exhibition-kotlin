package ecommerce.common.util

import org.springframework.format.datetime.standard.DateTimeFormatterFactory
import java.time.LocalDateTime

/**
 * Created by syoka on 2018/7/6.
 */
interface TimeFormatUtil{

    companion object {
        val FULL_FORMATTER = "yyyy-MM-dd HH:mm:ss "
        val NORMAL_FORMATTER = "yyyy-MM-dd"

        fun defaultFormatter(date: LocalDateTime):String{
            return DateTimeFormatterFactory(FULL_FORMATTER).createDateTimeFormatter().format(date);
        }

        fun normalFormatter(date: LocalDateTime):String{
            return DateTimeFormatterFactory(NORMAL_FORMATTER).createDateTimeFormatter().format(date);
        }
    }
}