package ecommerce.transaction.databean

import java.time.LocalDate

data class TrCrOrderCondition (
    //查询时间段
    var startDate: LocalDate,
    var endDate: LocalDate
)