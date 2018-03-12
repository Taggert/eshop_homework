package com.eshop.service;

import com.eshop.model.exceptions.GeneralAPIException;
import com.eshop.model.exceptions.NoSuchProductException;
import com.eshop.model.exceptions.NoSuchUserException;
import com.eshop.model.web.PurchaseResponce;

import java.util.List;

public interface PurchaseService {

   // PurchaseResponce save(PurchaseRequest purchaseWeb, String sessionId) throws NoSuchProductException, NoSuchUserException, GeneralAPIException;
    PurchaseResponce buy(Integer productId, Integer quantity, String sessionId) throws NoSuchProductException, NoSuchUserException, GeneralAPIException;
    List<PurchaseResponce> getBuys(String sessionId, String username);

}