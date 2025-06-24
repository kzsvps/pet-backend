package com.example.restapi.player.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String account;
    private String password;

    private String nickname;
    private String gender;
    private String city;
    private String birthday;
}
