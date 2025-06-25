package com.example.restapi.hamster.repository;

import com.example.restapi.hamster.entity.Hamster;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HamsterRepository extends JpaRepository<Hamster, Long> {
    List<Hamster> findByPlayerId(Long playerId);
}