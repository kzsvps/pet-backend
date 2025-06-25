package com.example.restapi.shop.service.impl;

import com.example.restapi.shop.dto.ShopRegisterRequest;
import com.example.restapi.shop.entity.Shop;
import com.example.restapi.shop.repository.ShopRepository;
import com.example.restapi.shop.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopRepository shopRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public Shop registerShop(ShopRegisterRequest request) {
        Optional<Shop> existing = shopRepository.findByAccount(request.getAccount());
        if (existing.isPresent()) {
            return null; // 帳號已存在
        }

        Shop shop = new Shop();
        shop.setAccount(request.getAccount());
        shop.setPassword(passwordEncoder.encode(request.getPassword()));

        // 第一階段只存帳號密碼，後面基本資料再更新
        return shopRepository.save(shop);
    }

    // 可加 updateProfile 方法，負責更新店名、地址、電話、city、type 等
}
