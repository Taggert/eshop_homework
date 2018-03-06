package com.eshop.controller;


import com.eshop.model.Product;
import com.eshop.model.web.ProductRequest;
import com.eshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/create")
    public Product create(@RequestBody ProductRequest productRequest) {
        return productService.create(productRequest);
    }

    @GetMapping("/all")
    public List<Product> getAll() {
        return productService.findAll();
    }

    @GetMapping("/search/id")
    public Product searchById(@RequestParam("productid") Integer productId) {
        return productService.findById(productId);
    }

    @GetMapping("/search/name")
    public List<Product> searchByName(@RequestParam("productname") String productName) {
        return productService.findByName(productName);
    }

    @GetMapping("/search/seller")
    public List<Product> searchBySellerId(@RequestParam("sellerid") Integer sellerId) {
        return productService.findBySellerId(sellerId);
    }

    @GetMapping("/search/price")
    public List<Product> searchByPrice(@RequestParam(value = "minprice", required = false) Double minPrice,
                                       @RequestParam(value = "maxprice", required = true) Double maxPrice) {
        return productService.findByPrice(minPrice, maxPrice);
    }

    @GetMapping("/search/cat")
    public List<Product> searchByCategoryId(@RequestParam(value = "catid") Integer categoryId) {
        return productService.findByCategory(categoryId);
    }

    @DeleteMapping("/delete/{productId}")
    public void deleteByProductId(@PathVariable("productId") Integer productId){
        productService.delete(productId);
    }

}