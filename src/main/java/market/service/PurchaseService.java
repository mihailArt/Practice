package market.service;

import market.entity.Purchase;
import market.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PurchaseService {

    @Autowired
    PurchaseRepository purchaseRepository;

    public Purchase getById(int id){
        return purchaseRepository.findById(id);
    }

    public void save(Purchase purchase){
        purchaseRepository.save(purchase);
    }

    public void delete(int id){
        Purchase purchase = purchaseRepository.findById(id);
        purchaseRepository.delete(purchase);
    }

    public List<Purchase> getAll(){
        return purchaseRepository.findAll();
    }
}
