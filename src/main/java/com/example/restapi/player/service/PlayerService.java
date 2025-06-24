package com.example.restapi.player.service;

import com.example.restapi.player.dto.PlayerRegisterRequest;
import com.example.restapi.player.entity.Player;
import com.example.restapi.player.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    public Player register(PlayerRegisterRequest req) {
        if (playerRepository.existsByAccount(req.getAccount())) {
            throw new RuntimeException("帳號已被註冊");
        }

        Player player = new Player();
        player.setAccount(req.getAccount());
        player.setPassword(req.getPassword());
        player.setNickname(req.getNickname());
        player.setGender(req.getGender());
        player.setCity(req.getCity());
        player.setBirthday(req.getBirthday());

        return playerRepository.save(player);
    }
}
