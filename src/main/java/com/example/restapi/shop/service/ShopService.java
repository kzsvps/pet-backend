package com.example.restapi.shop.service;

import com.example.restapi.shop.dto.ShopRegisterRequest;
import com.example.restapi.shop.entity.Shop;

public interface ShopService {
    Shop registerShop(ShopRegisterRequest request);
    // 你可以加上 updateProfile 等其他方法
}
