package com.example.restapi.player.controller;

import com.example.restapi.player.dto.PlayerRegisterRequest;
import com.example.restapi.player.entity.Player;
import com.example.restapi.player.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/player")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @PostMapping("/register")
    public Player register(@RequestBody PlayerRegisterRequest req) {
        return playerService.register(req);
    }
}
