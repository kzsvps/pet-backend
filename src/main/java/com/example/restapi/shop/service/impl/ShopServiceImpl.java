package com.example.restapi.shop.service.impl;

import com.example.restapi.shop.dto.*;
import com.example.restapi.shop.entity.Shop;
import com.example.restapi.shop.enums.City;
import com.example.restapi.shop.enums.ShopType;
import com.example.restapi.shop.repository.ShopRepository;
import com.example.restapi.shop.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopRepository shopRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public Shop registerShop(ShopRegisterRequest request) {
        if (shopRepository.findByAccount(request.getAccount()).isPresent()) {
            return null;
        }

        Shop shop = new Shop();
        shop.setAccount(request.getAccount());
        shop.setPassword(passwordEncoder.encode(request.getPassword()));

        return shopRepository.save(shop);
    }

    @Override
    public boolean updateProfile(ShopUpdateProfileRequest request) {
        Optional<Shop> shopOpt = shopRepository.findById(request.getShopId());
        if (shopOpt.isEmpty()) {
            return false;
        }

        Shop shop = shopOpt.get();
        shop.setShopName(request.getShopName());
        shop.setAddress(request.getAddress());
        shop.setPhone(request.getPhone());
        shop.setCity(request.getCity());
        shop.setType(request.getType());

        shopRepository.save(shop);
        return true;
    }

    // ===== 新增的實作方法 =====

    @Override
    public ShopListResponse getShops(int page, int size, String type, String city, String search) {
        // 建立分頁參數（按ID降序排列，最新的在前面）
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());

        Page<Shop> shopPage;

        // 根據篩選條件執行不同的查詢
        try {
            City cityEnum = (city != null && !city.equals("全部")) ? City.fromLabel(city) : null;
            ShopType typeEnum = (type != null && !type.equals("全部")) ? ShopType.fromLabel(type) : null;
            boolean hasSearch = search != null && !search.trim().isEmpty();

            if (hasSearch && cityEnum != null && typeEnum != null) {
                // 完整複合查詢
                shopPage = shopRepository.findByShopNameContainingIgnoreCaseAndCityAndType(search, cityEnum, typeEnum,
                        pageable);
            } else if (hasSearch && cityEnum != null) {
                // 關鍵字 + 城市
                shopPage = shopRepository.findByShopNameContainingIgnoreCaseAndCity(search, cityEnum, pageable);
            } else if (hasSearch && typeEnum != null) {
                // 關鍵字 + 類型
                shopPage = shopRepository.findByShopNameContainingIgnoreCaseAndType(search, typeEnum, pageable);
            } else if (cityEnum != null && typeEnum != null) {
                // 城市 + 類型
                shopPage = shopRepository.findByCityAndType(cityEnum, typeEnum, pageable);
            } else if (hasSearch) {
                // 只有關鍵字
                shopPage = shopRepository.findByShopNameContainingIgnoreCase(search, pageable);
            } else if (cityEnum != null) {
                // 只有城市
                shopPage = shopRepository.findByCity(cityEnum, pageable);
            } else if (typeEnum != null) {
                // 只有類型
                shopPage = shopRepository.findByType(typeEnum, pageable);
            } else {
                // 無篩選條件，查詢所有
                shopPage = shopRepository.findAll(pageable);
            }
        } catch (IllegalArgumentException e) {
            // 如果城市或類型轉換失敗，返回所有資料
            shopPage = shopRepository.findAll(pageable);
        }

        // 轉換為 ShopItemDTO 列表
        List<ShopItemDTO> shopItems = shopPage.getContent().stream()
                .map(ShopItemDTO::new)
                .collect(Collectors.toList());

        return new ShopListResponse(
                shopItems,
                shopPage.getNumber(),
                shopPage.getTotalPages(),
                shopPage.getTotalElements());
    }

    @Override
    public ShopItemDTO getShopById(Integer shopId) {
        Optional<Shop> shopOpt = shopRepository.findById(shopId);
        if (shopOpt.isPresent()) {
            return new ShopItemDTO(shopOpt.get());
        }
        return null;
    }
}