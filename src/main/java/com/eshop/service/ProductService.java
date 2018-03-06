package com.eshop.service;

import com.eshop.model.Category;
import com.eshop.model.Product;
import com.eshop.model.web.ProductRequest;

import java.util.List;

public interface ProductService {

    Product create(ProductRequest productRequest);
    List<Product> findAll();
    Product findById(Integer id);
    List<Product> findByName(String name);
    List<Product> findBySellerId(Integer sellerId);
    List<Product> findByPrice(Double minPrice, Double maxPrice);
    List<Product> findByCategory(Integer categoryId);
    void delete(Integer productId);
    Category createCategory(String name);
}