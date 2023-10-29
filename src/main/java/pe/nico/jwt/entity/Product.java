package pe.nico.jwt.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;
    private String productName;

    @Column(length = 2000)
    private String productDescription;

    private Double productDiscountedPrice;
    private Double productActualPrice;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "product_images", joinColumns = {@JoinColumn(name = "product_id")},
               inverseJoinColumns = {@JoinColumn(name = "image_id")})
    private Set<ImageModel> productImages;
}
