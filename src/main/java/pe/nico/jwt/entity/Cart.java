package pe.nico.jwt.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cartId;
    @OneToOne
    private Product product;
    @OneToOne
    private User user;

    public Cart(Product product, User user) {
        this.product = product;
        this.user = user;
    }
}
