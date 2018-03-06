package com.eshop.service.Impl;

import com.eshop.model.Category;
import com.eshop.model.Product;
import com.eshop.model.web.ProductRequest;
import com.eshop.repository.CategoryRepository;
import com.eshop.repository.ProductRepository;
import com.eshop.repository.UserRepository;
import com.eshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImp implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public Product create(ProductRequest productWeb) {
        Product product = new Product();
        product = product.builder()
                .displayName(productWeb.getDisplayName())
                .description(productWeb.getDescription())
                .category(!categoryRepository.existsById(productWeb.getCategoryId()) ?
                        productService.createCategory(productWeb.getCategoryName()) : categoryRepository.findById(productWeb.getCategoryId()).get())
                .price(productWeb.getPrice())
                .discount(productWeb.getDiscount() == null ? 0 : productWeb.getDiscount())
                .seller(    userRepository.findById(productWeb.getUserId()).get())
                .quantity(productWeb.getQuantity())
                .build();
        productRepository.save(product);
        return product;
    }


    @Override
    @Transactional
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    @Transactional
    public Product findById(Integer id) {
        return productRepository.findById(id).get();
    }

    @Override
    @Transactional
    public List<Product> findByName(String name) {
        return productRepository.findByDisplayName(name);
    }

    @Override
    @Transactional
    public List<Product> findBySellerId(Integer sellerId) {
        return productRepository.findBySellerId(sellerId);
    }

    @Override
    @Transactional
    public List<Product> findByPrice(Double minPrice, Double maxPrice) {
        minPrice = minPrice == null ? 0 : minPrice;
        return productRepository.findByPriceBetween(minPrice, maxPrice);
    }

    @Override
    @Transactional
    public List<Product> findByCategory(Integer categoryId) {
        if (categoryRepository.findById(categoryId) != null) {
            return productRepository.findByCategory(categoryRepository.getOne(categoryId));
        }
        return null;
    }

    @Override
    @Transactional
    public void delete(Integer productId) {
        productRepository.deleteById(productId);
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Category createCategory(String name) {
        Category category = new Category();
        category.setName(name);
        categoryRepository.save(category);
        return category;
    }
}