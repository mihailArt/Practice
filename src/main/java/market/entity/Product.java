package market.entity;

import javax.persistence.*;

@Entity
@Table (name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="idproduct")
    private int idProduct;

    @Column(name="nameproduct")
    private String nameProduct;

    @Column(name="cost")
    private int cost;

    @Column(name="category")
    private String category;

    Product(){ }

    Product(int idProduct, String nameProduct){
        this.idProduct = idProduct;
        this.nameProduct = nameProduct;
    }

    public int getIdProduct(){ return idProduct; }

    public void setIdProduct(int value){ idProduct = value; }

    public String getNameProduct(){
        return nameProduct;
    }

    public void setNameProduct(String value){ nameProduct = value; }

    public int getCost(){ return cost;}

    public void setCost(int value){cost = value;}

    public String getCategory(){return category;}

    public void setCategory(String value){category = value;}

    @Override
    public String toString() {
        return String.format(
                "Product[id=%d, name='%s', cost=%d, category='%s']",
                idProduct, nameProduct, cost, category);
    }
}
