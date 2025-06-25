package com.example.restapi.shop.repository;

import com.example.restapi.shop.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ShopRepository extends JpaRepository<Shop, Integer> {
    Optional<Shop> findByAccount(String account);

    // ✅ 新增：登入時查詢帳號＋密碼
    Optional<Shop> findByAccountAndPassword(String account, String password);
}
