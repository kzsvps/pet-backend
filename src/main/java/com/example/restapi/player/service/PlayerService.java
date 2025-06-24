package com.example.restapi.player.service;

import com.example.restapi.player.dto.PlayerRegisterRequest;
import com.example.restapi.player.dto.PlayerUpdateProfileRequest;
import com.example.restapi.player.entity.Player;

public interface PlayerService {

    Player registerPlayer(PlayerRegisterRequest request);

    boolean updateProfile(PlayerUpdateProfileRequest request);

    boolean validateLogin(String account, String password);
}
