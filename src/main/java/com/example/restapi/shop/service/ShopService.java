package com.example.restapi.shop.service;

import com.example.restapi.shop.dto.ShopRegisterRequest;
import com.example.restapi.shop.dto.ShopUpdateProfileRequest;
import com.example.restapi.shop.dto.ShopListResponse;
import com.example.restapi.shop.dto.ShopItemDTO;
import com.example.restapi.shop.entity.Shop;

public interface ShopService {

    Shop registerShop(ShopRegisterRequest request);

    boolean updateProfile(ShopUpdateProfileRequest request);

    // ===== 新增的方法 =====

    // 獲取商家列表（支援分頁、篩選、搜尋）
    ShopListResponse getShops(int page, int size, String type, String city, String search);

    // 根據ID獲取單一商家詳細資訊
    ShopItemDTO getShopById(Integer shopId);
}