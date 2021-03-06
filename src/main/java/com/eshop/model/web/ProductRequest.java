package com.eshop.model.web;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductRequest {

    private String displayName;

    private String description;

    private Integer categoryId;

    private String categoryName;

    private Double price;

    private Integer discount;

    private Integer quantity;


}