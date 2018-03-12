package com.eshop.model;

import com.eshop.model.web.ProductResponse;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "PRODUCTS")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "DISPLAYED_NAME", nullable = false, length = 50)
    private String displayedName;

    @Column(name = "DESCRIPTION")
    private String description;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID", nullable = false)
    private Category category;

    @Column(name = "PRICE", nullable = false)
    private Double price;

    @Column(name = "DISCOUNT")
    private Integer discount;

    @ManyToOne
    @JoinColumn(name = "SELLER", nullable = false)
    private User seller;

    @Column(name = "QUANTITY", nullable = false)
    private Integer quantity;

    /*@JsonIgnore
    private UserResponceWithoutId sellerResponse = seller.createResponseWithoutId();*/

    public  ProductResponse createResponse(){

        return ProductResponse.builder()
                .id(id)
                .displayedName(displayedName)
                .description(description)
                .category(category)
                .quantity(quantity)
                .price(price)
                .discount(discount)
                .build();
    }
}