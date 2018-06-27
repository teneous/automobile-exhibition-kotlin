package ecommerce.transaction.service.impl

import ecommerce.transaction.common.SYOKA
import ecommerce.transaction.repository.IOrderSheetRepository
import ecommerce.transaction.service.ITrGenerateSequenceNoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.Duration
import java.time.LocalDateTime

/**
 * Created by syoka on 2018/6/22.
 */
@Service
@Transactional
class TrGenerateTimeSequenceNoServiceImpl: ITrGenerateSequenceNoService {


    override fun generateSequenceNo(): String {
        return "$SYOKA${Duration.between( LocalDateTime.of(1994,7,1,0,0,0),LocalDateTime.now()).toMillis()}"
    }
}


