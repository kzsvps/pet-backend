package com.example.restapi.player.controller;

import com.example.restapi.player.dto.PlayerRegisterRequest;
import com.example.restapi.player.dto.PlayerUpdateProfileRequest;
import com.example.restapi.player.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/player")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    // 註冊
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody PlayerRegisterRequest request) {
        boolean success = playerService.registerPlayer(request);
        if (success) {
            return ResponseEntity.ok("註冊成功，請繼續填寫基本資料。");
        } else {
            return ResponseEntity.badRequest().body("註冊失敗，帳號已存在。");
        }
    }

    // 更新基本資料
    @PostMapping("/updateProfile")
    public ResponseEntity<String> updateProfile(@RequestBody PlayerUpdateProfileRequest request) {
        boolean success = playerService.updateProfile(request);
        if (success) {
            return ResponseEntity.ok("資料更新成功！");
        } else {
            return ResponseEntity.badRequest().body("更新失敗，找不到該玩家。");
        }
    }

    // 登入驗證 (可選)
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
