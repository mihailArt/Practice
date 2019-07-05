package market.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "purchase")
@Getter
@Setter
@ToString
public class Purchase extends BaseEntity {

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn (name="idproduct")
    private Product product;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn (name="idclient")
    private Client client;

    @Column
    private Date date;

    @Column
    private int cost;

    @Column
    private int number;
}
