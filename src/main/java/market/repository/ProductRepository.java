package market.repository;

import market.entity.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Integer>{

    List<Product> findByIdProduct(int id);
}
