package com.eshop.model;

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

    @Column(name = "DISPLAY_NAME", nullable = false, length = 50)
    private String displayName;

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



}