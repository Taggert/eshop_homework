package com.eshop.controller;

import com.eshop.model.Purchase;
import com.eshop.model.web.PurchaseResponce;
import com.eshop.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchases")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    /*@PostMapping("/save")
    public Purchase create(@RequestBody PurchaseRequest purchaseWeb,
                           @RequestHeader("Authorization") String sessionId) throws GeneralAPIException {
       return purchaseService.save(purchaseWeb, sessionId);
    }*/

    @PostMapping("/buy")
    public PurchaseResponce buy(@RequestParam("productId") Integer productId,
                        @RequestParam("quantity") Integer quantity,
                        @RequestHeader("Authorization") String sessionId){
        return purchaseService.buy(productId, quantity, sessionId);
    }

    @GetMapping("/getbuys")
    public List<PurchaseResponce> getBuys(@RequestHeader("Authorization") String sessionId,
                                  @RequestParam(value = "username", required = false) String username){
        return purchaseService.getBuys(sessionId, username);
    }

}