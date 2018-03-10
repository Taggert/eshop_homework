package com.eshop.service.Impl;

import com.eshop.model.Product;
import com.eshop.model.Purchase;
import com.eshop.model.Role;
import com.eshop.model.User;
import com.eshop.model.exceptions.*;
import com.eshop.model.web.PurchaseRequest;
import com.eshop.repository.ProductRepository;
import com.eshop.repository.PurchaseRepository;
import com.eshop.repository.UserRepository;
import com.eshop.repository.UserSessionRepository;
import com.eshop.service.PurchaseService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@NoArgsConstructor
public class PurchaseServiceImpl implements PurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserSessionRepository userSessionRepository;

    @Override
    @Transactional(rollbackFor = GeneralAPIException.class)
    public Purchase save(PurchaseRequest purchaseWeb, String sessionId) throws GeneralAPIException {
        Purchase purchase = new Purchase();
        Product product;
        User buyer = userSessionRepository.findBySessionId(sessionId).getUser();
        //Check product
        if (productRepository.existsById(purchaseWeb.getProductId())) {
            product = productRepository.findById(purchaseWeb.getProductId()).get();
            purchase.setProduct(product);
            purchase.setSoldBy(product.getSeller());
            purchase.setPriceWithoutDiscount(product.getPrice());
            purchase.setEndPrice(product.getPrice() - (product.getPrice() * (product.getDiscount().doubleValue() / 100)));
        } else {
            throw new NoSuchProductException("No such product");
        }
        User seller = product.getSeller();
        purchase.setBoughtBy(buyer);
        //Check quantity
        if (purchaseWeb.getQuantity() <= product.getQuantity()) {
            purchase.setQuantity(purchaseWeb.getQuantity());
            product.setQuantity(product.getQuantity() - purchaseWeb.getQuantity());
        } else {
            throw new NotEnoughProductsException("Not enough products");
        }
        //Check money
        if (purchase.getEndPrice() <= buyer.getBalance()) {
            seller.setBalance(seller.getBalance() + purchase.getEndPrice());
            buyer.setBalance(buyer.getBalance() - purchase.getEndPrice());
        } else {
            throw new InsufficiendFundsException("Not enough money");
        }

        purchaseRepository.save(purchase);
        return purchase;
    }

    @Override
    @Transactional(rollbackFor = GeneralAPIException.class)
    public Purchase buy(Integer productId, Integer quantity, String sessionId) throws GeneralAPIException {
        Purchase purchase = new Purchase();
        Product product;
        User buyer = userSessionRepository.findBySessionId(sessionId).getUser();
        //Check product
        if (productRepository.existsById(productId)) {
            product = productRepository.findById(productId).get();
            purchase.setProduct(product);
            purchase.setSoldBy(product.getSeller());
            purchase.setPriceWithoutDiscount(product.getPrice());
            purchase.setEndPrice(product.getPrice() - (product.getPrice() * (product.getDiscount().doubleValue() / 100)));
        } else {
            throw new NoSuchProductException("No such product");
        }
        User seller = product.getSeller();
        purchase.setBoughtBy(buyer);
        //Check quantity
        if (quantity <= product.getQuantity()) {
            purchase.setQuantity(quantity);
            product.setQuantity(product.getQuantity() - quantity);
        } else {
            throw new NotEnoughProductsException("Not enough products");
        }
        //Check money
        if (purchase.getEndPrice() <= buyer.getBalance()) {
            seller.setBalance(seller.getBalance() + purchase.getEndPrice());
            buyer.setBalance(buyer.getBalance() - purchase.getEndPrice());
        } else {
            throw new InsufficiendFundsException("Not enough money");
        }

        purchaseRepository.save(purchase);
        return purchase;
    }

    @Override
    @Transactional
    public List<Product> getSells(String sessionId, String username) {
        User user = userSessionRepository.getBySessionId(sessionId).getUser();
        if (username != null && user.getRoles().contains(Role.ADMIN)) {
            User byUsername = userRepository.getByUsername(username);
            if (byUsername == null) {
                throw new NoSuchUserException("User with username " + username + "doesn't exists");
            }
            List<Product> bySeller = productRepository.getBySeller(byUsername);
            if (bySeller == null) {
                throw new NotEnoughProductsException("No products");
            }
            return bySeller;
        }
        List<Product> bySeller = productRepository.getBySeller(user);
        if (bySeller == null) {
            throw new NotEnoughProductsException("No products");
        }
        return bySeller;
    }

    @Override
    @Transactional
    public List<Purchase> getBuys(String sessionId, String username) {
        User user = userSessionRepository.getBySessionId(sessionId).getUser();
        if (username != null && user.getRoles().contains(Role.ADMIN)) {
            User byUsername = userRepository.getByUsername(username);
            if (byUsername == null) {
                throw new NoSuchUserException("User with username " + username + "doesn't exists");
            }
            List<Purchase> byBoughtBy = purchaseRepository.getByBoughtBy(byUsername);
            if (byBoughtBy == null) {
                throw new NoSuchProductException("No purchases");
            }
            return byBoughtBy;
        }
        List<Purchase> byBoughtBy = purchaseRepository.getByBoughtBy(user);
        if (byBoughtBy == null) {
            throw new NoSuchProductException("No purchases");
        }
        return byBoughtBy;

    }
}