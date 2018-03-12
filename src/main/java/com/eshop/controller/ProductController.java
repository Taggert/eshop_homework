package com.eshop.controller;


import com.eshop.model.Product;
import com.eshop.model.Role;
import com.eshop.model.User;
import com.eshop.model.exceptions.NotYourProductException;
import com.eshop.model.web.ProductRequest;
import com.eshop.model.web.ProductResponse;
import com.eshop.model.web.ProductUpdateRequest;
import com.eshop.repository.UserSessionRepository;
import com.eshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private UserSessionRepository userSessionRepository;

    @PostMapping("/create")
    public Product create(@RequestBody ProductRequest productRequest,
                          @RequestHeader("Authorization") String sessionId) {
        Product product = productService.create(productRequest, sessionId);
        return product;
    }

    @PutMapping("/update/{productId}")
    public ProductResponse update(@RequestBody ProductUpdateRequest productUpdateRequest,
                          @PathVariable("productId") Integer productId,
                          @RequestHeader("Authorization") String sessionId) {
        return productService.update(productUpdateRequest, productId, sessionId);
    }

    @GetMapping("/all")
    public List<ProductResponse> getAll() {
        return productService.findAll();
    }

   /* @GetMapping("/search/id")
    public Product searchById(@RequestParam("productid") Integer productId) {
        return productService.findById(productId);
    }*/

    @GetMapping("/search/name")
    public List<ProductResponse> searchByName(@RequestParam("productname") String productName) {
        return productService.findByName(productName);
    }

    @GetMapping("/search/seller")
    public List<ProductResponse> searchBySellerId(@RequestParam("username") String username) {
        return productService.findBySeller(username);
    }

    @GetMapping("/search/price")
    public List<ProductResponse> searchByPrice(@RequestParam(value = "minprice", required = false) Double minPrice,
                                       @RequestParam(value = "maxprice", required = true) Double maxPrice) {
        return productService.findByPrice(minPrice, maxPrice);
    }

    @GetMapping("/search/cat")
    public List<ProductResponse> searchByCategoryId(@RequestParam(value = "catid") Integer categoryId) {
        return productService.findByCategory(categoryId);
    }

    @GetMapping("/searchParams")
    public List<ProductResponse> search(@RequestParam(value = "name", required = false) String productName,
                                @RequestParam(value = "catid", required = false) Integer categoryId,
                                @RequestParam(value = "isDescending", required = false, defaultValue = "true") Boolean isDesc) {
        return productService.findByParamsy(productName, categoryId, isDesc);
    }

    @GetMapping("/getsells")
    public List<ProductResponse> getSells(@RequestHeader("Authorization") String sessionId,
                                          @RequestParam(value = "username", required = false) String username) {

        return productService.getSells(sessionId, username);
    }

    @DeleteMapping("/delete/{productId}")
    public void deleteByProductId(@PathVariable("productId") Integer productId, @RequestHeader("Authorization") String sessionId) throws NotYourProductException {
        User user = userSessionRepository.findBySessionId(sessionId).getUser();
        if (productService.findById(productId).getSeller().equals(user) || user.getRoles().contains(Role.ADMIN)) {
            productService.delete(productId);
        } else {
            throw new NotYourProductException("Only seller can delete his product");
        }
    }

}