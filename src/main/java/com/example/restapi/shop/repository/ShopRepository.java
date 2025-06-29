package com.example.restapi.shop.repository;

import com.example.restapi.shop.entity.Shop;
import com.example.restapi.shop.enums.City;
import com.example.restapi.shop.enums.ShopType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

public interface ShopRepository extends JpaRepository<Shop, Integer> {
    Optional<Shop> findByAccount(String account);

    Optional<Shop> findByAccountAndPassword(String account, String password);

    // ===== 新增的查詢方法 =====

    // 1. 根據商家名稱搜尋（模糊查詢）
    Page<Shop> findByShopNameContainingIgnoreCase(String shopName, Pageable pageable);

    // 2. 根據城市篩選
    Page<Shop> findByCity(City city, Pageable pageable);

    // 3. 根據類型篩選
    Page<Shop> findByType(ShopType type, Pageable pageable);

    // 4. 複合查詢：城市 + 類型
    Page<Shop> findByCityAndType(City city, ShopType type, Pageable pageable);

    // 5. 複合查詢：關鍵字 + 城市
    Page<Shop> findByShopNameContainingIgnoreCaseAndCity(String shopName, City city, Pageable pageable);

    // 6. 複合查詢：關鍵字 + 類型
    Page<Shop> findByShopNameContainingIgnoreCaseAndType(String shopName, ShopType type, Pageable pageable);

    // 7. 完整複合查詢：關鍵字 + 城市 + 類型
    Page<Shop> findByShopNameContainingIgnoreCaseAndCityAndType(String shopName, City city, ShopType type,
            Pageable pageable);

    // 8. 自定義查詢：只查詢有完整資料的商家
    @Query("SELECT s FROM Shop s WHERE s.shopName IS NOT NULL AND s.address IS NOT NULL AND s.phone IS NOT NULL")
    Page<Shop> findCompleteShops(Pageable pageable);
}