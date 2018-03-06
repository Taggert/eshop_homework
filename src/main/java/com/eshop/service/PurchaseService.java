package com.eshop.service;

import com.eshop.model.Purchase;
import com.eshop.model.exceptions.AbstractAPIException;
import com.eshop.model.exceptions.NoSuchProductException;
import com.eshop.model.exceptions.NoSuchUserException;
import com.eshop.model.web.PurchaseRequest;

public interface PurchaseService {

    Purchase save(PurchaseRequest purchaseWeb) throws NoSuchProductException, NoSuchUserException, AbstractAPIException;


}