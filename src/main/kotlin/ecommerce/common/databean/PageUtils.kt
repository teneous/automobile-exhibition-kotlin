package ecommerce.common.databean

import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort

/**
 * Created by syoka on 2018/6/25.
 */
class PageUtils{
    companion object {
        private val DEFAULT_SORT = Sort(Sort.Direction.fromString("id"), "desc")
    }

    fun basicalPage(pagenum :Int?,size :Int?,sort :List<Pair<String,String>>?):Pageable {
        sort ?: ArrayList()
        sort?.forEach {
            DEFAULT_SORT
        }
        val sortResult = Sort(Sort.Direction.fromString(sort!!.first), sort.second)
        return PageRequest.of(pagenum ?: 1, size ?: 20, sortResult)
    }
}