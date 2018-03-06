package com.eshop.service.Impl;

import com.eshop.model.Product;
import com.eshop.model.Purchase;
import com.eshop.model.User;
import com.eshop.model.exceptions.*;
import com.eshop.model.web.PurchaseRequest;
import com.eshop.repository.ProductRepository;
import com.eshop.repository.PurchaseRepository;
import com.eshop.repository.UserRepository;
import com.eshop.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(rollbackFor = AbstractAPIException.class)
    public Purchase save(PurchaseRequest purchaseWeb) throws AbstractAPIException {
        Purchase purchase = new Purchase();
        Product product;
        User seller;
        User buyer;
        //Check product
        if (productRepository.existsById(purchaseWeb.getProductId())) {
            product = productRepository.findById(purchaseWeb.getProductId()).get();
            purchase.setProduct(product);
            purchase.setSoldBy(product.getSeller());
            purchase.setPriceWithoutDiscount(product.getPrice());
            purchase.setEndPrice(product.getPrice()-(product.getPrice()*(product.getDiscount().doubleValue()/100)));
        } else {
            throw new NoSuchProductException("No such product");
        }
        //Check buyer
        if (userRepository.existsById(purchaseWeb.getBoughtById())) {
            seller = purchase.getSoldBy();
            buyer = userRepository.findById(purchaseWeb.getBoughtById()).get();
            purchase.setBoughtBy(buyer);
        } else {
            throw new NoSuchUserException("No such user (buyer)");
        }
        //Check quantity
        if(purchaseWeb.getQuantity()<=product.getQuantity()){
            purchase.setQuantity(purchaseWeb.getQuantity());
            product.setQuantity(product.getQuantity()-purchaseWeb.getQuantity());
        }else{
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
}