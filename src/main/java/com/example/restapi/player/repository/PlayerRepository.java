package com.example.restapi.player.repository;

import com.example.restapi.player.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    boolean existsByAccount(String account);
}
