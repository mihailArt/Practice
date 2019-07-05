package market.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "client")
@Getter
@Setter
@ToString
public class Client extends BaseEntity{

    @Column(name ="clientname")
    private String clientName;

    @Column(name = "password")
    private String password;

}
