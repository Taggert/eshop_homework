package com.eshop.service;

import com.eshop.model.Category;
import com.eshop.model.Product;
import com.eshop.model.web.ProductRequest;
import com.eshop.model.web.ProductResponse;
import com.eshop.model.web.ProductUpdateRequest;

import java.util.List;

public interface ProductService {

    Product create(ProductRequest productRequest, String sessionId);
    List<ProductResponse> findAll();
    ProductResponse update(ProductUpdateRequest productUpdateRequest, Integer productId, String sessionId);
    Product findById(Integer id);
    List<ProductResponse> findByName(String name);
    List<ProductResponse> findBySeller(String username);
    List<ProductResponse> findByPrice(Double minPrice, Double maxPrice);
    List<ProductResponse> findByCategory(Integer categoryId);
    void delete(Integer productId);
    Category createCategory(String name);
    List<ProductResponse> findByParamsy(String productName, Integer categoryId, Boolean isDesc);
    List<ProductResponse> getSells(String sessionId, String username);
}