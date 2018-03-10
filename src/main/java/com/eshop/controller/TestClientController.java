package com.eshop.controller;

import com.eshop.client.EShopClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestClientController {

    @Autowired
    private EShopClient eShopClient;

    @GetMapping("/start")
    public void start(){
        eShopClient.start();
    }

}