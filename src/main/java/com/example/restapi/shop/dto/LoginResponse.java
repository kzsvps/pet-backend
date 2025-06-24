package com.example.restapi.dto;

public class LoginResponse {
    private String status;
    private String account;
    private int type;

    public LoginResponse(String status, String account, int type) {
        this.status = status;
        this.account = account;
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public String getAccount() {
        return account;
    }

    public int getType() {
        return type;
    }
}
