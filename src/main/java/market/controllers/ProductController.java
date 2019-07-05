package market.controllers;

import market.entity.*;
import market.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Product> getProduct(@PathVariable("id") Integer id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Product product = productService.getById(id);

        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAll();

        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Product> saveProduct(@RequestBody @Valid Product product) {
        HttpHeaders headers = new HttpHeaders();

        if (product == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Product checkProduct = productService.getById(product.getId());
        if (checkProduct == null){
            productService.save(product);
            return new ResponseEntity<>(product, headers, HttpStatus.CREATED);
        }
        else
            return new ResponseEntity<>(checkProduct, headers, HttpStatus.CONFLICT);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Integer id, @RequestBody @Valid Product product, UriComponentsBuilder builder) {
        HttpHeaders headers = new HttpHeaders();

        if (product == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Product checkProduct = productService.getById(id);

        if(checkProduct != null){
            checkProduct.setNameProduct(product.getNameProduct());
            checkProduct.setCost(product.getCost());
            checkProduct.setCategory(product.getCategory());
            productService.save(checkProduct);
            return new ResponseEntity<>(product, headers, HttpStatus.OK);
        }
        else
            return  new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Product> deleteProduct(@PathVariable("id") Integer id) {
        Product product = productService.getById(id);

        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        productService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

