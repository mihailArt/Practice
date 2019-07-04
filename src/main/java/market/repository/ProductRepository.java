package market.repository;

import market.entity.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface ProductRepository extends CrudRepository<Product, Integer> {
    Product findById(int id);
    List<Product> findAll();
}
