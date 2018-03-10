package com.eshop.service;

import com.eshop.model.Product;
import com.eshop.model.Purchase;
import com.eshop.model.exceptions.GeneralAPIException;
import com.eshop.model.exceptions.NoSuchProductException;
import com.eshop.model.exceptions.NoSuchUserException;
import com.eshop.model.web.PurchaseRequest;

import java.util.List;

public interface PurchaseService {

    Purchase save(PurchaseRequest purchaseWeb, String sessionId) throws NoSuchProductException, NoSuchUserException, GeneralAPIException;
    Purchase buy(Integer productId, Integer quantity, String sessionId) throws NoSuchProductException, NoSuchUserException, GeneralAPIException;
    List<Product> getSells(String sessionId, String username);
    List<Purchase> getBuys(String sessionId, String username);

}