package com.example.restapi.player.service;

import com.example.restapi.player.dto.PlayerRegisterRequest;
import com.example.restapi.player.dto.PlayerUpdateProfileRequest;

public interface PlayerService {
    boolean registerPlayer(PlayerRegisterRequest request);

    boolean updateProfile(PlayerUpdateProfileRequest request);

    boolean validateLogin(String account, String password);
}
