package com.example.restapi.player.repository;

import com.example.restapi.player.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    Optional<Player> findByAccount(String account);
}
