package com.example.restapi.shop.service.impl;

import com.example.restapi.shop.dto.ShopRegisterRequest;
import com.example.restapi.shop.dto.ShopUpdateProfileRequest;
import com.example.restapi.shop.entity.Shop;
import com.example.restapi.shop.enums.ShopType;
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
        if (shopRepository.findByAccount(request.getAccount()).isPresent()) {
            return null; // 帳號已存在
        }

        Shop shop = new Shop();
        shop.setAccount(request.getAccount());
        shop.setPassword(passwordEncoder.encode(request.getPassword()));

        return shopRepository.save(shop);
    }

    @Override
    public boolean updateProfile(ShopUpdateProfileRequest request) {
        Optional<Shop> shopOpt = shopRepository.findById(request.getShopId());
        if (shopOpt.isEmpty()) {
            return false;
        }

        Shop shop = shopOpt.get();
        shop.setShopName(request.getShopName());
        shop.setAddress(request.getAddress());
        shop.setPhone(request.getPhone());
        shop.setCity(request.getCity());
        // 這裡轉換
        shop.setType(ShopType.fromString(request.getType()));

        shopRepository.save(shop);
        return true;
    }

}
