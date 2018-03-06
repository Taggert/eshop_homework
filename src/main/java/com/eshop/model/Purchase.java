package com.eshop.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "PURCHASES")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "PRODUCT_ID", nullable = false)
    private Product product;

    @OneToOne
    @JoinColumn(name = "SOLD_BY", nullable = false)
    private User soldBy;

    @OneToOne
    @JoinColumn(name = "BOUGHT_BY", nullable = false)
    private User boughtBy;


    @Column(name = "QUANTITY", nullable = false)
    private Integer quantity;

    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    private Date sellDate;

    @Column(name = "FINAL_PRICE", nullable = false)
    private Double endPrice;

    @Column(name = "START_PRICE", nullable = false)
    private Double priceWithoutDiscount;




}