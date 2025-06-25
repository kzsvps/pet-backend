package com.example.restapi.loginsystem;

import com.example.restapi.player.entity.Player;
import com.example.restapi.shop.entity.Shop;
import com.example.restapi.player.repository.PlayerRepository;
import com.example.restapi.shop.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private ShopRepository shopRepository;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        // 嘗試用帳號密碼找 Player
        Optional<Player> optionalPlayer = playerRepository.findByAccountAndPassword(
                request.getAccount(), request.getPassword());

        if (optionalPlayer.isPresent()) {
            Player player = optionalPlayer.get();
            LoginResponse res = new LoginResponse();
            res.setRole("player");
            res.setId(player.getId());
            res.setNickname(player.getNickname());
            return res;
        }

        // 嘗試用帳號密碼找 Shop
        Optional<Shop> optionalShop = shopRepository.findByAccountAndPassword(
                request.getAccount(), request.getPassword());

        if (optionalShop.isPresent()) {
            Shop shop = optionalShop.get();
            LoginResponse res = new LoginResponse();
            res.setRole("shop");
            res.setId(shop.getId().longValue()); // Shop 的 id 是 Integer，轉成 Long
            res.setShopName(shop.getShopName());
            res.setType(shop.getType().name());
            res.setAddress(shop.getAddress());
            res.setPhone(shop.getPhone());
            res.setCity(shop.getCity().name());
            return res;
        }

        // 若兩邊都查不到，拋出錯誤
        throw new RuntimeException("帳號或密碼錯誤");
    }
}
