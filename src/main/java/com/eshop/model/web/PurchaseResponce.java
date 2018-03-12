package com.eshop.model.web;

import com.eshop.model.Product;
import com.eshop.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@Data
public class PurchaseResponce {

    private Integer id;

    @JsonIgnore
    private Product product;

    @JsonIgnore
    private User boughtBy;

    private Integer quantity;

    private Date sellDate;

    private Double endPrice;

    private Double priceWithoutDiscount;

    private ProductResponse productResponse;

    private UserResponceWithoutId userResponceWithoutId;

    public PurchaseResponce(Integer id, Product product, User boughtBy, Integer quantity, Date sellDate, Double endPrice, Double priceWithoutDiscount) {
        this.id = id;
        this.product = product;
        this.boughtBy = boughtBy;
        this.quantity = quantity;
        this.sellDate = sellDate;
        this.endPrice = endPrice;
        this.priceWithoutDiscount = priceWithoutDiscount;
        this.productResponse = product.createResponse();
        this.userResponceWithoutId = boughtBy.createResponseWithoutId();
    }
}