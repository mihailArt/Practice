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

    public void save(Product product){
        productRepository.save(product);
    }

    public void delete(int id){
        Product product = productRepository.findById(id);
        productRepository.delete(product);
    }

    public List<Product> getAll(){
        return productRepository.findAll();
    }
}


