package com.example.restapi.controller;

import com.example.restapi.dto.LoginRequest;
import com.example.restapi.dto.LoginResponse;
import com.example.restapi.model.User;
import com.example.restapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        Optional<User> userOpt = userRepository.findByAccount(loginRequest.getAccount());

        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (user.getPassword().equals(loginRequest.getPassword())) {
                return new LoginResponse("success", user.getAccount(), user.getType());
            }
        }

        return new LoginResponse("fail", "", -1);
    }
}
