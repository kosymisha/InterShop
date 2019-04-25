package com.someshop.intershop.controller;

import com.someshop.intershop.model.Shop;
import com.someshop.intershop.model.User;
import com.someshop.intershop.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Controller
public class ShopController {

    @Autowired
    private ShopService shopService;

    @GetMapping("/shops")
    public String shops(Model model) {
        model.addAttribute("shops", shopService.findAll()); //dto
        return "shop/shops";
    }

    @GetMapping("/shops/my")
    @PreAuthorize("hasAuthority('SELLER')")
    public String myShops (Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("shops", shopService.findByOwner(user.getId().toString()));
        return "shop/shops";
    }

    @GetMapping("/shops/{shop}")
    public String shopsById (@PathVariable Shop shop, Model model,
                             @AuthenticationPrincipal User user) {
        model.addAttribute("shop", shop);
        model.addAttribute("userId", user.getId());
        return "shop/shop";
    }

    @DeleteMapping("/shops/{shop}")
    @ResponseBody
    public void deleteShop (@PathVariable Shop shop, @AuthenticationPrincipal User user) {
        shopService.delete(shop, user);
    }

    @GetMapping("shops/create")
    public String createShop () {
        return "shop/create";
    }

    @PostMapping("shops/create")
    @PreAuthorize("hasAuthority('SELLER')")
    public String createShop (@AuthenticationPrincipal User user, Model model,
                              @RequestParam Map<String, String> form,
                              @RequestParam("photo_url") MultipartFile file) throws IOException {
        Shop shop = shopService.create(form, file, user);
        if (shop != null) return "redirect:/shops/" + shop.getId();
        else { model.addAttribute("message", "Shop with that name already exists"); return "shop/create"; }
    }

    @GetMapping("/shops/{shop}/options")
    @PreAuthorize("hasAuthority('SELLER')")
    public String options (@PathVariable Shop shop, Model model) {
        model.addAttribute("shop", shop);
        return "shop/options";
    }

    @PutMapping("/shops/{shop}/options")
    @PreAuthorize("hasAuthority('SELLER')")
    public String optionsSave (@PathVariable Shop shop, @AuthenticationPrincipal User user, Model model,
                               @RequestParam Map<String, String> form, @RequestParam("photo_url") MultipartFile file) {
        Shop newShop =  shopService.saveInfo(shop, user, form, file);
        if (newShop != null) {model.addAttribute("message","Success"); return "shop/options";}
        else { model.addAttribute("message","Shop with that name already exists"); return "shop/options"; }
    }
}
