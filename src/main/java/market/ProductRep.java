package market;

import java.util.*;

import org.springframework.stereotype.Repository;

@Repository
public class ProductRep {
    private static final Map<Integer, Product> empMap = new HashMap<Integer, Product>();

    static {
        initPrds();
    }

    private static void initPrds() {
        Product prd1 = new Product(1, "Smith");
        Product prd2 = new Product(2, "Allen");
        Product prd3 = new Product(3, "Jones");

        empMap.put(prd1.getId(), prd1);
        empMap.put(prd2.getId(), prd2);
        empMap.put(prd3.getId(), prd3);
    }

    public Product getProduct(int id) {
        return empMap.get(id);
    }

    public Product addProduct(Product prd) {
        empMap.put(prd.getId(), prd);
        return prd;
    }

    public Product updateProduct(Product prd) {
        empMap.put(prd.getId(), prd);
        return prd;
    }

    public void deleteProduct(int id) {
        empMap.remove(id);
    }

    public List<Product> getAllProducts() {
        Collection<Product> c = empMap.values();
        List<Product> list = new ArrayList<Product>();
        list.addAll(c);
        return list;
    }
}
