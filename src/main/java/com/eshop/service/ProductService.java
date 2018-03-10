package com.eshop.service;

import com.eshop.model.Category;
import com.eshop.model.Product;
import com.eshop.model.web.ProductRequest;
import com.eshop.model.web.ProductUpdateRequest;

import java.util.List;

public interface ProductService {

    Product create(ProductRequest productRequest, String sessionId);
    List<Product> findAll();
    Product update(ProductUpdateRequest productUpdateRequest, Integer productId, String sessionId);
    Product findById(Integer id);
    List<Product> findByName(String name);
    List<Product> findBySeller(String username);
    List<Product> findByPrice(Double minPrice, Double maxPrice);
    List<Product> findByCategory(Integer categoryId);
    void delete(Integer productId);
    Category createCategory(String name);
    List<Product> findByParamsy(String productName, Integer categoryId, Boolean isDesc);
}