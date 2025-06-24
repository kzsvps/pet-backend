package com.example.restapi.player.controller;

import com.example.restapi.player.dto.PlayerRegisterRequest;
import com.example.restapi.player.entity.Player;
import com.example.restapi.player.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/player")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody PlayerRegisterRequest request) {
        try {
            Player player = playerService.registerPlayer(request);
            if (player != null) {
                Map<String, Object> response = new HashMap<>();
                response.put("playerId", player.getId());
                response.put("account", player.getAccount());
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.badRequest().body("註冊失敗，帳號已存在。");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("伺服器錯誤：" + e.getMessage());
        }
    }
}
