// Controller: HamsterController.java
package com.example.restapi.hamster.controller;

import com.example.restapi.hamster.dto.HamsterCreateRequest;
import com.example.restapi.hamster.entity.Hamster;
import com.example.restapi.hamster.service.HamsterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hamster")
public class HamsterController {

    @Autowired
    private HamsterService hamsterService;

    @PostMapping("/create")
    public Hamster createHamster(@RequestBody HamsterCreateRequest req) {
        return hamsterService.createHamster(req);
    }

    @GetMapping("/player/{playerId}")
    public List<Hamster> getHamstersByPlayer(@PathVariable Long playerId) {
        return hamsterService.getHamstersByPlayerId(playerId);
    }
}