package com.eshop.model.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductRequest {

    private String displayName;

    private String description;

    private Integer categoryId;

    private String categoryName;

    private Double price;

    private Integer discount;

    private Integer userId;

    private Integer quantity;


}