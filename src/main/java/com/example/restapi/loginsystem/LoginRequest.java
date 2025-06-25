package com.example.restapi.loginsystem;

import lombok.Data;

@Data
public class LoginRequest {
    private String account;
    private String password;
}
