package market.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@MappedSuperclass
@Setter
@Getter
@ToString
public class BaseEntity {

    @Id
    @GeneratedValue
    @Column(name="id")
    private int id;
}
