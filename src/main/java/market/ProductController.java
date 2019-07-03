package market;

import java.util.List;
import org.springframework.web.bind.annotation.*;


@RestController
public class ProductController {
    private ProductRep productRep = new ProductRep();

    @RequestMapping("/")
    @ResponseBody
    public String welcome() {
        return "Welcome to RestTemplate Example.";
    }

    // URL:
    // http://localhost:8080/products
    // http://localhost:8080/products.xml
    // http://localhost:8080/products.json
    @RequestMapping("/products")
    @ResponseBody
    public List<Product> getProducts() {
        List<Product> list = productRep.getAllProducts();
        return list;
    }

    // URL:
    // http://localhost:8080/product/{id}
    // http://localhost:8080/product/{id}.xml
    // http://localhost:8080/product/{id}.json
    @RequestMapping("/product/{id}")
    @ResponseBody
    public Product getProduct(@PathVariable("id") int id) {
        return productRep.getProduct(id);
    }

}
