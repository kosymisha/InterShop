package com.someshop.sneakershop.controller;

import com.someshop.sneakershop.model.Role;
import com.someshop.sneakershop.model.Shop;
import com.someshop.sneakershop.model.User;
import com.someshop.sneakershop.repository.ShopRepository;
import com.someshop.sneakershop.service.ShopService;
import com.someshop.sneakershop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ShopController {

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private ShopService shopService;

    @GetMapping("/shops")
    public String shops(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("shopList", shopRepository.findAllOrderByOwner(user));
        model.addAttribute("user", user);
        return "shop/shops";
    }

    @GetMapping("/shops/{shop}")
    public String shopsById (@PathVariable Shop shop, Model model) {
        model.addAttribute("shop", shop);
        return "shop/shop";
    }

    @GetMapping("/shops/{shop}/delete")
    public String deleteShop (@PathVariable Shop shop, @AuthenticationPrincipal User user, Model model) {
        shopService.delete(shop, user);
        model.addAttribute("shopList", shopRepository.findAll());
        model.addAttribute("user", user);
        return "shop/shops";
    }
}
