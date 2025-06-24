package com.example.restapi.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.restapi.shop.entity.Shop;

import java.util.Optional;

public interface ShopRepository extends JpaRepository<Shop, Long> {
    Optional<Shop> findByAccount(String account);
}
