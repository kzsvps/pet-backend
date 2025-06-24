package com.example.restapi.player.service.impl;

import com.example.restapi.player.dto.PlayerRegisterRequest;
import com.example.restapi.player.dto.PlayerUpdateProfileRequest;
import com.example.restapi.player.entity.Player;
import com.example.restapi.player.repository.PlayerRepository;
import com.example.restapi.player.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public boolean registerPlayer(PlayerRegisterRequest request) {
        // 檢查帳號是否存在
        if (playerRepository.findByAccount(request.getAccount()).isPresent()) {
            return false;
        }

        Player player = new Player();
        player.setAccount(request.getAccount());

        // 密碼加密
        player.setPassword(passwordEncoder.encode(request.getPassword()));

        playerRepository.save(player);
        return true;
    }

    @Override
    public boolean updateProfile(PlayerUpdateProfileRequest request) {
        Optional<Player> playerOpt = playerRepository.findById(request.getPlayerId());
        if (playerOpt.isEmpty()) {
            return false;
        }

        Player player = playerOpt.get();
        player.setNickname(request.getNickname());
        player.setGender(request.getGender());
        player.setRegion(request.getRegion());
        player.setBirthday(request.getBirthday());

        playerRepository.save(player);
        return true;
    }

    @Override
    public boolean validateLogin(String account, String password) {
        Optional<Player> playerOpt = playerRepository.findByAccount(account);
        if (playerOpt.isEmpty())
            return false;

        Player player = playerOpt.get();
        return passwordEncoder.matches(password, player.getPassword());
    }
}
