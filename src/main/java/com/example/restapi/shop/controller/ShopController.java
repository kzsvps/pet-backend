package com.example.restapi.shop.controller;

import com.example.restapi.shop.dto.ShopRegisterRequest;
import com.example.restapi.shop.entity.Shop;
import com.example.restapi.shop.service.ShopService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shop")
public class ShopController {

    @Autowired
    private ShopService shopService;

    @PostMapping("/register")
    public Shop registerShop(@RequestBody ShopRegisterRequest req) {
        return shopService.register(req);
    }
}
