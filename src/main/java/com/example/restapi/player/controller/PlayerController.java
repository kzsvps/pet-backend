package com.example.restapi.player.controller;

import com.example.restapi.player.dto.PlayerRegisterRequest;
import com.example.restapi.player.dto.PlayerUpdateProfileRequest;
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
            System.out.println("Account: " + request.getAccount());
            System.out.println("Password: " + request.getPassword());

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

    @PostMapping("/updateProfile")
    public ResponseEntity<String> updateProfile(@RequestBody PlayerUpdateProfileRequest request) {
        boolean success = playerService.updateProfile(request);
        if (success) {
            return ResponseEntity.ok("資料更新成功！");
        } else {
            return ResponseEntity.badRequest().body("更新失敗，找不到該玩家。");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody PlayerRegisterRequest request) {
        boolean valid = playerService.validateLogin(request.getAccount(), request.getPassword());
        if (valid) {
            return ResponseEntity.ok("登入成功");
        } else {
            return ResponseEntity.status(401).body("帳號或密碼錯誤");
        }
    }
}
