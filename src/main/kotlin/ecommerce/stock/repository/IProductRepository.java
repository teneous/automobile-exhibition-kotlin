package ecommerce.stock.repository;

import ecommerce.transaction.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 写着写着发现自己用的java
 * Created by syoka on 2018/7/9.
 */
public interface IProductRepository extends JpaRepository<Product,Long> {
    List<Product> findByProductIdIn(List<String> productIdList);

    Product findByProductId(String productId);
}
