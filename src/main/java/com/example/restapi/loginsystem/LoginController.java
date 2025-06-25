package com.example.restapi.loginsystem;

import com.example.restapi.player.entity.Player;
import com.example.restapi.shop.entity.Shop;
import com.example.restapi.player.repository.PlayerRepository;
import com.example.restapi.shop.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        // Step 1: 查詢玩家
        Optional<Player> optionalPlayer = playerRepository.findByAccount(request.getAccount());
        if (optionalPlayer.isPresent()) {
            Player player = optionalPlayer.get();
            if (passwordEncoder.matches(request.getPassword(), player.getPassword())) {
                LoginResponse res = new LoginResponse();
                res.setRole("player");
                res.setId(player.getId());
                res.setNickname(player.getNickname());

                // 補空值給 Unity JsonUtility 不會報錯
                res.setShopName("");
                res.setType("");
                res.setAddress("");
                res.setPhone("");
                res.setCity("");

                return res;
            }
        }

        // Step 2: 查詢商家
        Optional<Shop> optionalShop = shopRepository.findByAccount(request.getAccount());
        if (optionalShop.isPresent()) {
            Shop shop = optionalShop.get();
            if (passwordEncoder.matches(request.getPassword(), shop.getPassword())) {
                LoginResponse res = new LoginResponse();
                res.setRole("shop");
                res.setId(shop.getId().longValue());
                res.setShopName(shop.getShopName());
                res.setType(shop.getType().name());
                res.setAddress(shop.getAddress());
                res.setPhone(shop.getPhone());
                res.setCity(shop.getCity().name());

                // 補空值給 Unity JsonUtility 不會報錯
                res.setNickname("");

                return res;
            }
        }

        throw new RuntimeException("帳號或密碼錯誤");
    }
}
