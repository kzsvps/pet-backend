package com.example.restapi.controller;

import com.example.restapi.dto.ShopRegisterRequest;
import com.example.restapi.entity.Shop;
import com.example.restapi.service.ShopService;
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
