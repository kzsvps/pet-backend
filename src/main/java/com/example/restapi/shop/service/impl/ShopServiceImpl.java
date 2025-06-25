package com.example.restapi.shop.service.impl;

import com.example.restapi.shop.dto.ShopRegisterRequest;
import com.example.restapi.shop.dto.ShopUpdateProfileRequest;
import com.example.restapi.shop.entity.Shop;
import com.example.restapi.shop.enums.ShopType;
import com.example.restapi.shop.repository.ShopRepository;
import com.example.restapi.shop.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopRepository shopRepository;

    @Override
    public Shop registerShop(ShopRegisterRequest request) {
        // 先檢查帳號是否已存在（假設帳號唯一）
        if (shopRepository.findByAccount(request.getAccount()).isPresent()) {
            return null; // 帳號已存在
        }

        Shop shop = new Shop();
        shop.setAccount(request.getAccount());
        shop.setPassword(request.getPassword()); // 密碼最好要加密，這邊依你實作調整

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

        try {
            ShopType shopType = ShopType.fromString(request.getType());
            shop.setType(shopType);
        } catch (IllegalArgumentException e) {
            return false; // 類型錯誤
        }

        shopRepository.save(shop);
        return true;
    }
}
