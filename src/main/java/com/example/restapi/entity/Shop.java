package com.example.restapi.entity;

import com.example.restapi.enums.City;
import com.example.restapi.enums.ShopType;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Data;

@Data
@Entity
@Table(name = "shops")
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String shopName;

    @Column(nullable = false, unique = true)
    private String account;

    @Column(nullable = false)
    private String password;

    private String address;

    private String phone;

    @Enumerated(EnumType.STRING)
    private City city;

    @Enumerated(EnumType.STRING)
    private ShopType type;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    // Getter / Setter（可用 Lombok 簡化）
}
