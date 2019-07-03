package market;

public class Product {

    private int id;
    private String productName;

    Product(int id, String productName){
        this.id = id;
        this.productName = productName;
    }

    public int getId(){
        return id;
    }

    public void setId(int value){
        id = value;
    }

    public String getProductName(){
        return productName;
    }

    public void setProductName(String value){
        productName = value;
    }
}
