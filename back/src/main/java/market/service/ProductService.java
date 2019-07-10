package market.service;

import market.entity.Product;
import market.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public Product getById(int id){
        return productRepository.findById(id);
    }

    public Product save(Product product){
        return productRepository.save(product);
    }

    public void deleteById(int id){
        productRepository.deleteById(id);
    }

    public List<Product> getAll(){
        return productRepository.findAll();
    }
}


