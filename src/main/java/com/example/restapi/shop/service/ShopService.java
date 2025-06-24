package com.example.restapi.shop.service;

import com.example.restapi.shop.dto.ShopRegisterRequest;
import com.example.restapi.shop.entity.Shop;
import com.example.restapi.shop.repository.ShopRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopService {

    @Autowired
    private ShopRepository shopRepository;

    public Shop register(ShopRegisterRequest req) {
        Shop shop = new Shop();
        shop.setShopName(req.getShopName());
        shop.setAccount(req.getAccount());
        shop.setPassword(req.getPassword());
        shop.setAddress(req.getAddress());
        shop.setPhone(req.getPhone());
        shop.setCity(req.getCity());
        shop.setType(req.getType());
        return shopRepository.save(shop);
    }
}
