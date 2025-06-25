package com.example.restapi.shop.controller;

import com.example.restapi.shop.dto.ShopRegisterRequest;
import com.example.restapi.shop.dto.ShopUpdateProfileRequest;
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
        Shop shop = shopService.registerShop(request);
        if (shop != null) {
            Map<String, Object> res = new HashMap<>();
            res.put("shopId", shop.getId());
            res.put("account", shop.getAccount());
            return ResponseEntity.ok(res);
        }
        return ResponseEntity.badRequest().body("註冊失敗，帳號已存在。");
    }

    @PostMapping("/updateProfile")
    public ResponseEntity<String> updateProfile(@RequestBody ShopUpdateProfileRequest request) {
        boolean success = shopService.updateProfile(request);
        if (success)
            return ResponseEntity.ok("資料更新成功！");
        return ResponseEntity.badRequest().body("更新失敗，找不到該商家。");
    }
}
