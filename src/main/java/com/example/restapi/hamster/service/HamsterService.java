// Service: HamsterService.java
package com.example.restapi.hamster.service;

import com.example.restapi.hamster.dto.HamsterCreateRequest;
import com.example.restapi.hamster.entity.Hamster;
import com.example.restapi.hamster.repository.HamsterRepository;
import com.example.restapi.player.entity.Player;
import com.example.restapi.player.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HamsterService {

    @Autowired
    private HamsterRepository hamsterRepository;

    @Autowired
    private PlayerRepository playerRepository;

    public Hamster createHamster(HamsterCreateRequest request) {
        Optional<Player> playerOpt = playerRepository.findById(request.getPlayerId());
        if (playerOpt.isEmpty()) {
            throw new RuntimeException("找不到該玩家");
        }

        Hamster hamster = new Hamster();
        hamster.setName(request.getName());
        hamster.setHunger(100);
        hamster.setMood(100);
        hamster.setPlayer(playerOpt.get());

        return hamsterRepository.save(hamster);
    }

    public List<Hamster> getHamstersByPlayerId(Long playerId) {
        return hamsterRepository.findByPlayerId(playerId);
    }
}
