package com.example.restapi.shop.dto;

import com.example.restapi.shop.enums.City;
import com.example.restapi.shop.enums.ShopType;

import lombok.Data;

@Data
public class ShopRegisterRequest {
    private String shopName;
    private String account;
    private String password;
    private String address;
    private String phone;
    private City city;
    private ShopType type;
}
