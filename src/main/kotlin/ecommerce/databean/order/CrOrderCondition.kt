package ecommerce.databean.order

import java.time.LocalDate

data class CrOrderCondition (
    //查询时间段
    var startDate: LocalDate,
    var endDate: LocalDate
)