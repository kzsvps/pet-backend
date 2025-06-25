package com.example.restapi.hamster.dto;

import lombok.Data;

@Data
public class HamsterCreateRequest {
    private String name;
    private Long playerId;
}