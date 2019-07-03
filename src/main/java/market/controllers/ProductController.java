package market.controllers;

import java.util.List;

import market.entity.Product;
import market.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class ProductController {
    @Autowired
    private ProductRepository productRep;

    @RequestMapping("/")
    @ResponseBody
    public String welcome() {
        return "Welcome to RestTemplate Example.";
    }

    // URL:
    // http://localhost:8080/products
    @GetMapping("/products")
    @ResponseBody
    public Iterable<Product> getProducts() {
        return productRep.findAll();
    }

    // URL:
    // http://localhost:8080/product/{id}
    @RequestMapping("/product/{id}")
    @ResponseBody
    public List<Product> getProduct(@PathVariable("id") int id) {
        return productRep.findByIdProduct(id);
    }

}
