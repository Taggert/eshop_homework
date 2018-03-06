package com.eshop.repository;

import com.eshop.model.Category;
import com.eshop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findByDisplayName(String name);
    List<Product> findBySellerId(Integer userId);
    List<Product> findByPriceBetween(Double minPrice, Double maxPrice);
    List<Product> findByCategory(Category category);


}