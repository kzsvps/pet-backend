package com.example.restapi.player.dto;

public class PlayerRegisterRequest {
    private String account;
    private String password;

    // getters and setters
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
