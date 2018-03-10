package com.eshop.client;

import com.eshop.model.Product;
import com.eshop.model.User;
import com.eshop.model.web.ProductRequest;
import com.eshop.model.web.ProductUpdateRequest;
import com.eshop.model.web.UserRequest;
import com.eshop.service.ProductService;
import com.eshop.service.PurchaseService;
import com.eshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EShopClient {

    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;
    @Autowired
    private PurchaseService purchaseService;

    public void start() {
        List<User> users = createUsers();
        List<Product> products = createProducts(users);
        makePurchases(users, products);
       /* updateProduct(users, products);
        makePurchases(users, products);*/


    }

    private List<User> createUsers() {
        UserRequest userAdmin = UserRequest.builder()
                .username("Taggert")
                .password("123123")
                .email("taggert@mail.ru")
                .firstname("Evgen")
                .lastname("Lvov")
                .build();

        UserRequest userBuyer = UserRequest.builder()
                .username("Alisa")
                .password("123123")
                .email("alisa@mail.ru")
                .firstname("Alisa")
                .lastname("Lvov")
                .build();

        UserRequest userSeller = UserRequest.builder()
                .username("Anna")
                .password("123123")
                .email("anna@mail.ru")
                .firstname("Anna")
                .lastname("Lvov")
                .build();

        User userA = userService.create(userAdmin);
        User userB = userService.create(userBuyer);
        User userS = userService.create(userSeller);
        userService.promoteUser("Anna");
        userService.updateBalance(555.55, userB.getId());
        List<User> users = new ArrayList<>();
        users.add(userA);
        users.add(userB);
        users.add(userS);
        return users;
    }

    private List<Product> createProducts(List<User> users) {
        ProductRequest productPen = ProductRequest.builder()
                .displayName("Golden Parker Pen")
                .description("Nice golden pen")
                .categoryId(1)
                .categoryName("Pens&Pensils")
                .price(95.12)
                .discount(5)
                .quantity(7)
                .build();
        Product productPn = productService.create(productPen, users.get(2).getId());
        ProductRequest productPensil = ProductRequest.builder()
                .displayName("Pensil HB Kohinoor")
                .description("Nice pensil")
                .categoryId(1)
                .price(34.1)
                .discount(5)
                .quantity(6)
                .build();
        Product productPl = productService.create(productPensil, users.get(0).getId());
        List<Product> products = new ArrayList<>();
        products.add(productPn);
        products.add(productPl);
        return products;
    }

    private void makePurchases(List<User> users, List<Product> products) {
        purchaseService.buy(products.get(0).getId(), 2, users.get(1).getId());
        purchaseService.buy(products.get(1).getId(), 1, users.get(1).getId());
    }


    private void updateProduct(List<User> users, List<Product> products) {
        ProductUpdateRequest productUpdateRequest = ProductUpdateRequest.builder()
                .displayName("Pensil HBB KOOOOO")
                .discount(40)
                .build();
        productService.update(productUpdateRequest, products.get(1).getId(), users.get(0).getId());
    }

}