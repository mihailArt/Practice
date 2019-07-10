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

    @ManyToOne
    @JoinColumn (name="idproduct")
    private Product product;

    @ManyToOne
    @JoinColumn (name="idclient")
    private Client client;

    @Column
    private Date date;

    @Column
    private int cost;

    @Column
    private int number;
}
