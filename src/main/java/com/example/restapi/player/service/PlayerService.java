package com.example.restapi.player.service;

import com.example.restapi.player.dto.PlayerRegisterRequest;
import com.example.restapi.player.entity.Player;
import com.example.restapi.player.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Player registerPlayer(PlayerRegisterRequest request) throws Exception {
        Optional<Player> existing = playerRepository.findByAccount(request.getAccount());
        if (existing.isPresent()) {
            return null;
        }

        Player player = new Player();
        player.setAccount(request.getAccount());
        player.setPassword(passwordEncoder.encode(request.getPassword()));
        return playerRepository.save(player);
    }
}
