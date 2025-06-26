package com.example.restapi.hamster.repository;

import com.example.restapi.hamster.entity.Hamster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HamsterRepository extends JpaRepository<Hamster, Long> {

    // 注意！必須是 player._Id 才能對應到 @ManyToOne 關聯的 Player
    List<Hamster> findByPlayer_Id(Long playerId); // ✅ 正確的方法
}
