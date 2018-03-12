package com.eshop.model.web;

import com.eshop.model.Category;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProductResponse {


    private Integer id;

    private String displayedName;

    private String description;

    private Category category;

    private Double price;

    private Integer discount;

    private Integer quantity;



}