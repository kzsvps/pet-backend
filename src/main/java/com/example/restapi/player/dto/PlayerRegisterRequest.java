package com.example.restapi.player.dto;

import lombok.Data;

@Data
public class PlayerRegisterRequest {
    private String account;
    private String password;
    private String nickname;
    private String gender;
    private String city;
    private String birthday;
}
