package ecommerce.module

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Configuration
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.transaction.annotation.EnableTransactionManagement

/**
 * Created by syoka on 2018/3/28.
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
@EnableJpaRepositories(basePackages = ["ecommerce.module.customer.repository","ecommerce.module.master.repository","ecommerce.module.transaction.repository"])
@EntityScan(basePackages = ["ecommerce.module.customer.entity","ecommerce.module.master.entity","ecommerce.module.transaction.entity"])
class JpaConfiguration