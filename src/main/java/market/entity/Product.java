package market.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table (name = "product")
@Getter
@Setter
@ToString
public class Product extends BaseEntity{

    @Column(name="nameproduct")
    private String nameProduct;

    @Column(name="cost")
    private int cost;

    @Column(name="category")
    private String category;
}
