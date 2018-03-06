package com.eshop.controller;

import com.eshop.model.Purchase;
import com.eshop.model.exceptions.AbstractAPIException;
import com.eshop.model.web.PurchaseRequest;
import com.eshop.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/purchases")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @PostMapping("/save")
    public Purchase create(@RequestBody PurchaseRequest purchaseWeb) throws AbstractAPIException {
       return purchaseService.save(purchaseWeb);
    }

}