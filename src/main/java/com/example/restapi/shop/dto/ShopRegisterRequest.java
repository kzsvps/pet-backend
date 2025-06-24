package com.example.restapi.dto;

import com.example.restapi.enums.City;
import com.example.restapi.enums.ShopType;
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
