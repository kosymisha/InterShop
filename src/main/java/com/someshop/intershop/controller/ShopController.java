package com.someshop.intershop.controller;

import com.someshop.intershop.model.Shop;
import com.someshop.intershop.model.User;
import com.someshop.intershop.repository.ShopRepository;
import com.someshop.intershop.service.ShopService;
import com.someshop.intershop.service.impl.CommentServiceImpl;
import com.someshop.intershop.service.impl.ShopServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Controller
public class ShopController {

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private ShopServiceImpl shopService;

    @Autowired
    private CommentServiceImpl commentService;

    @GetMapping("/shops")
    public String shops(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("shopList", shopRepository.findAllOrderByOwner(user));
        model.addAttribute("user", user);
        return "shop/shops";
    }

    @GetMapping("/shops/{shop}")
    public String shopsById (@PathVariable Shop shop, Model model,
                             @AuthenticationPrincipal User user) {
        model.addAttribute("shop", shop);
        model.addAttribute("user", user);
        model.addAttribute("comments", commentService.findAllByShop(shop));
        model.addAttribute("object", shop);
        return "shop/shop";
    }

    @GetMapping("/shops/{shop}/delete")
    public String deleteShop (@PathVariable Shop shop, @AuthenticationPrincipal User user, Model model) {
        shopService.delete(shop, user);
        model.addAttribute("shopList", shopRepository.findAll());
        model.addAttribute("user", user);
        return "shop/shops";
    }

    @GetMapping("shops/create")
    public String createShop () {
        return "shop/create";
    }

    @PostMapping("shops/create")
    @PreAuthorize("hasAuthority('SELLER')")
    public String createShop (@AuthenticationPrincipal User user,
                              @RequestParam Map<String, String> form,
                              @RequestParam("photo_url") MultipartFile file) throws IOException {
        shopService.create(form, file, user);
        return "shop/create";
    }
}
