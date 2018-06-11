package ecommerce

import org.springframework.context.annotation.Bean
import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.interceptor.TransactionInterceptor
import java.util.*


/**
 * Created by syoka on 2018/6/11.
 */
@Configuration
class TransactionInterceptorConfig {

    @Bean(name = ["transactionInterceptor"])
    fun transactionInterceptor(platformTransactionManager: PlatformTransactionManager): TransactionInterceptor {
        val transactionInterceptor = TransactionInterceptor()
        // 事物管理器
        transactionInterceptor.transactionManager = platformTransactionManager
        val transactionAttributes = Properties()
        // 新增
        transactionAttributes.setProperty("insert*","PROPAGATION_REQUIRED,-Throwable")
        // 修改
        transactionAttributes.setProperty("update*","PROPAGATION_REQUIRED,-Throwable")
        // 删除
        transactionAttributes.setProperty("delete*","PROPAGATION_REQUIRED,-Throwable")
        //查询
        transactionAttributes.setProperty("select*","PROPAGATION_REQUIRED,-Throwable,readOnly")
        transactionInterceptor.setTransactionAttributes(transactionAttributes)
        return transactionInterceptor
    }

    //代理到ServiceImpl的Bean
    @Bean
    fun transactionAutoProxy(): BeanNameAutoProxyCreator {
        val transactionAutoProxy = BeanNameAutoProxyCreator()
        transactionAutoProxy.isProxyTargetClass = true
        transactionAutoProxy.setBeanNames("*ServiceImpl")
        transactionAutoProxy.setInterceptorNames("transactionInterceptor")
        return transactionAutoProxy
    }
}