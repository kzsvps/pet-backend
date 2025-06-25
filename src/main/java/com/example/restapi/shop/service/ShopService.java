package com.example.restapi.shop.service;

import com.example.restapi.shop.dto.ShopRegisterRequest;
import com.example.restapi.shop.dto.ShopUpdateProfileRequest;
import com.example.restapi.shop.entity.Shop;

public interface ShopService {

    Shop registerShop(ShopRegisterRequest request);

    boolean updateProfile(ShopUpdateProfileRequest request);
}
