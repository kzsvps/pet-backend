package com.example.restapi.hamster.entity;

import com.example.restapi.player.entity.Player;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Hamster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private int hunger;
    private int mood;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id")
    private Player player;
}