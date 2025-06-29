package com.example.restapi.shop.dto;

import com.example.restapi.shop.entity.Shop;

public class ShopItemDTO {
    private Integer id; // 對應Unity的ID
    private String title; // 對應Unity的title (商家名稱)
    private String distance; // 對應Unity的distance
    private String introduce; // 對應Unity的introduce (簡介)
    private String city; // 對應Unity的city
    private String phone; // 對應Unity的phone
    private String address; // 對應Unity的address
    private String type; // 對應Unity的type

    // 建構子：從Shop實體轉換
    public ShopItemDTO(Shop shop) {
        this.id = shop.getId();
        this.title = shop.getShopName() != null ? shop.getShopName() : "未命名商家";
        this.distance = "0.0"; // 預設距離，後續可根據地理位置計算
        this.introduce = "歡迎來到 " + this.title; // 預設簡介
        this.city = shop.getCity() != null ? shop.getCity().getLabel() : "未知";
        this.phone = shop.getPhone() != null ? shop.getPhone() : "未提供";
        this.address = shop.getAddress() != null ? shop.getAddress() : "未提供地址";
        this.type = shop.getType() != null ? shop.getType().getLabel() : "未分類";
    }

    // 空建構子
    public ShopItemDTO() {
    }

    // Getters & Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}