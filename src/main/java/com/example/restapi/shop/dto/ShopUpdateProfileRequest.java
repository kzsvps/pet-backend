package com.example.restapi.shop.dto;

import com.example.restapi.shop.enums.City;
import com.example.restapi.shop.enums.ShopType;

public class ShopUpdateProfileRequest {
    private Integer shopId;
    private String shopName;
    private ShopType type; // ✅ 改為 enum
    private String address;
    private String phone;
    private City city; // ✅ 改為 enum

    // getters & setters
    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public ShopType getType() {
        return type;
    }

    public void setType(ShopType type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
