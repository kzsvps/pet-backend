package com.example.restapi.loginsystem;

import lombok.Data;

@Data
public class LoginResponse {
    private String role;
    private Long id;

    // Player
    private String nickname;

    // Shop
    private String shopName;
    private String type;
    private String address;
    private String phone;
    private String city;
}
