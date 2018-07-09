package ecommerce.stock.repository;

import ecommerce.transaction.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by syoka on 2018/7/9.
 */
public interface IProductRepository extends JpaRepository<Product,Long> {
    List<Product> findByProductIdIn(List<String> productIdList);
}
