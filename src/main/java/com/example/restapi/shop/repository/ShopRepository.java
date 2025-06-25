package com.example.restapi.shop.repository;

import com.example.restapi.shop.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShopRepository extends JpaRepository<Shop, Integer> {
    Optional<Shop> findByAccount(String account);
}
