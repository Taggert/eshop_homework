package com.eshop.model.web;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PurchaseRequest {

    private Integer productId;

    private Integer quantity;



}