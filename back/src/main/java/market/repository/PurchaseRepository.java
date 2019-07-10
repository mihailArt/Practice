package market.repository;

import market.entity.Purchase;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface PurchaseRepository extends CrudRepository<Purchase, Integer> {
    Purchase findById(int id);
    List<Purchase> findAll();
}