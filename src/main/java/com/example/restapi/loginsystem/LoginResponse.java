package com.example.restapi.loginsystem;

import lombok.Data;

@Data
public class LoginResponse {
    private String role; // "player" 或 "shop"

    // 共用欄位
    private Long id;

    // Player 專屬
    private String nickname;

    // Shop 專屬
    private String shopName;
    private String type;
    private String address;
    private String phone;
    private String city;
}
