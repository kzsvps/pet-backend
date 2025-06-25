package com.example.restapi.shop.controller;

import com.example.restapi.shop.dto.ShopRegisterRequest;
import com.example.restapi.shop.entity.Shop;
import com.example.restapi.shop.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/shop")
public class ShopController {

    @Autowired
    private ShopService shopService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody ShopRegisterRequest request) {
        try {
            Shop shop = shopService.registerShop(request);
            if (shop != null) {
                Map<String, Object> response = new HashMap<>();
                response.put("shopId", shop.getId());
                response.put("account", shop.getAccount());
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.badRequest().body("註冊失敗，帳號已存在。");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("伺服器錯誤：" + e.getMessage());
        }
    }

    // 其他路由如 updateProfile 可依需求加
}
