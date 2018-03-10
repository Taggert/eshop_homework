package com.eshop.repository;

import com.eshop.model.Category;
import com.eshop.model.Product;
import com.eshop.model.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findByDisplayedName(String name);
    List<Product> getBySeller(User user);
    List<Product> findByPriceBetween(Double minPrice, Double maxPrice);
    List<Product> findByCategory(Category category, Sort sort);
    List<Product> findByCategory(Category category);
    List<Product> findByDisplayedNameAndCategory(String name, Category category, Sort sort);




}