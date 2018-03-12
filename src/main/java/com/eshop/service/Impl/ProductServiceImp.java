package com.eshop.service.Impl;

import com.eshop.model.Category;
import com.eshop.model.Product;
import com.eshop.model.Role;
import com.eshop.model.User;
import com.eshop.model.exceptions.*;
import com.eshop.model.web.ProductRequest;
import com.eshop.model.web.ProductResponse;
import com.eshop.model.web.ProductUpdateRequest;
import com.eshop.repository.CategoryRepository;
import com.eshop.repository.ProductRepository;
import com.eshop.repository.UserRepository;
import com.eshop.repository.UserSessionRepository;
import com.eshop.service.ProductService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@NoArgsConstructor
public class ProductServiceImp implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserSessionRepository userSessionRepository;

    @Override
    @Transactional
    public Product create(ProductRequest productWeb, String sessioId) {
        Product product = new Product();
        product = product.builder()
                .displayedName(productWeb.getDisplayName())
                .description(productWeb.getDescription())
                .category(!categoryRepository.existsById(productWeb.getCategoryId()) ?
                        productService.createCategory(productWeb.getCategoryName()) : categoryRepository.findById(productWeb.getCategoryId()).get())
                .price(productWeb.getPrice())
                .discount(productWeb.getDiscount() == null ? 0 : productWeb.getDiscount())
                .seller(userSessionRepository.findBySessionId(sessioId).getUser())
                .quantity(productWeb.getQuantity())
                .build();
        return productRepository.save(product);

    }

    @Override
    @Transactional
    public List<ProductResponse> getSells(String sessionId, String username) {
        User user = userSessionRepository.getBySessionId(sessionId).getUser();
        if (username != null && user.getRoles().contains(Role.ADMIN)) {
            User byUsername = userRepository.getByUsername(username);
            if (byUsername == null) {
                throw new NoSuchUserException("User with username " + username + "doesn't exists");
            }
            List<ProductResponse> bySeller = productRepository.getBySeller(byUsername);
            if (bySeller == null) {
                throw new NotEnoughProductsException("No products");
            }
            return bySeller;
        }
        List<ProductResponse> bySeller = productRepository.getBySeller(user);
        if (bySeller == null) {
            throw new NotEnoughProductsException("No products");
        }
        return bySeller;
    }

    @Override
    @Transactional
    public ProductResponse update(ProductUpdateRequest productUpdateRequest, Integer productId, String sessionId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new NoSuchProductException("Product with ID [" + productId + "] not found"));
        User seller = product.getSeller();
        User you = userSessionRepository.findBySessionId(sessionId).getUser();
        if (seller.equals(you) || you.getRoles().contains(Role.ADMIN)) {
            product.setDisplayedName(productUpdateRequest.getDisplayName() == null ? product.getDisplayedName() : productUpdateRequest.getDisplayName());
            product.setDescription(productUpdateRequest.getDescription() == null ? product.getDescription() : productUpdateRequest.getDescription());
            product.setPrice(productUpdateRequest.getPrice() == null ? product.getPrice() : productUpdateRequest.getPrice());
            product.setDiscount(productUpdateRequest.getDiscount() == null ? product.getDiscount() : productUpdateRequest.getDiscount());
            product.setQuantity(productUpdateRequest.getQuantity() == null ? product.getQuantity() : productUpdateRequest.getQuantity());
            productRepository.save(product);
            return productRepository.findByIdAndDisplayedName(product.getId(), product.getDisplayedName());
        }
        throw new NotYourProductException("You are able to update only your products");
    }

    @Override
    @Transactional
    public List<ProductResponse> findAll() {
        return productRepository.findAllOrderById();
    }

    @Override
    @Transactional
    public Product findById(Integer id) {
        return productRepository.findById(id).get();
    }

    @Override
    @Transactional
    public List<ProductResponse> findByName(String name) {
        return productRepository.findByDisplayedName(name);
    }

    @Override
    @Transactional
    public List<ProductResponse> findBySeller(String username) {
        User seller = userRepository.getByUsername(username);
        if (seller == null) {
            throw new NoSuchUserException("User with username " + username + "doesn't exists");
        }
        return productRepository.getBySeller(seller);
    }

    @Override
    @Transactional
    public List<ProductResponse> findByPrice(Double minPrice, Double maxPrice) {
        minPrice = minPrice == null ? 0 : minPrice;
        return productRepository.findByPriceBetween(minPrice, maxPrice);
    }

    @Override
    @Transactional
    public List<ProductResponse> findByCategory(Integer categoryId) {
        if (categoryRepository.findById(categoryId) != null) {
            return productRepository.findByCategory(categoryRepository.findById(categoryId)
                    .orElseThrow(() -> new GeneralAPIException("No such category")));
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

    @Override
    @Transactional
    public List<ProductResponse> findByParamsy(String productName, Integer categoryId, Boolean isDesc) {

        Sort.Order productNameSort = Sort.Order
                .by("displayedName")
                .with(isDesc ? Sort.Direction.DESC : Sort.Direction.ASC);

        Sort sort = Sort.by(productNameSort);

        if (productName == null && categoryId == null) {
            return productRepository.findAllOrderById(sort);
        }
        if (productName == null) {
            return productRepository.findByCategory(
                    categoryRepository.findById(categoryId)
                            .orElseThrow(() -> new GeneralAPIException("No such category")),
                    sort);
        }
        if (categoryId == null) {
            List<ProductResponse> byDisplayedName = productRepository.findByDisplayedName(productName);
            if (byDisplayedName.isEmpty()) {
                throw new GeneralAPIException("Product with name " + productName + " doesn't exists");
            }
            return byDisplayedName;
        }
        List<ProductResponse> res = productRepository.findByDisplayedNameAndCategory(productName,
                categoryRepository.findById(categoryId)
                        .orElseThrow(() -> new GeneralAPIException("No such category")),
                sort);
        if (res.isEmpty()) {
            throw new GeneralAPIException("Product with name " + productName + " doesn't exists");
        }
        return res;
    }

}