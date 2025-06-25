package com.example.restapi.shop.dto;

public class ShopRegisterRequest {
    private String account;
    private String password;

    // Getter & Setter
    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
