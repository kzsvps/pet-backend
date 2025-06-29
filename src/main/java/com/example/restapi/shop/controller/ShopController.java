package com.example.restapi.shop.controller;

import com.example.restapi.shop.dto.*;
import com.example.restapi.shop.entity.Shop;
import com.example.restapi.shop.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/shop")
@CrossOrigin(origins = "*") // 允許Unity跨域請求
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

    @PostMapping("/updateProfile")
    public ResponseEntity<String> updateProfile(@RequestBody ShopUpdateProfileRequest request) {
        boolean success = shopService.updateProfile(request);
        if (success) {
            return ResponseEntity.ok("資料更新成功！");
        } else {
            return ResponseEntity.badRequest().body("更新失敗，找不到該商家。");
        }
    }

    // ===== 新增的API端點：對應Unity需求 =====

    /**
     * 獲取商家列表 - 支援分頁、篩選、搜尋
     * GET /api/shop/list?page=0&size=15&type=美食&city=台北市&search=關鍵字
     */
    @GetMapping("/list")
    public ResponseEntity<?> getShops(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "15") int size,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String search) {

        try {
            ShopListResponse response = shopService.getShops(page, size, type, city, search);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("獲取商家列表失敗：" + e.getMessage());
        }
    }

    /**
     * 獲取單一商家詳細資訊
     * GET /api/shop/{shopId}
     */
    @GetMapping("/{shopId}")
    public ResponseEntity<?> getShopById(@PathVariable Integer shopId) {
        try {
            ShopItemDTO shop = shopService.getShopById(shopId);
            if (shop != null) {
                return ResponseEntity.ok(shop);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("獲取商家詳細資訊失敗：" + e.getMessage());
        }
    }

    /**
     * 獲取所有可用的篩選選項
     * GET /api/shop/filters
     */
    @GetMapping("/filters")
    public ResponseEntity<?> getFilterOptions() {
        try {
            Map<String, Object> filters = new HashMap<>();

            // 城市選項
            String[] cities = { "全部", "台北市", "台中市", "高雄市" };
            filters.put("cities", cities);

            // 類型選項
            String[] types = { "全部", "美食", "飲品", "酒吧", "運動", "藝文" };
            filters.put("types", types);

            // 距離選項（如果需要的話）
            String[] distances = { "全部", "1公里內", "3公里內", "5公里內", "10公里內" };
            filters.put("distances", distances);

            return ResponseEntity.ok(filters);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("獲取篩選選項失敗：" + e.getMessage());
        }
    }
}