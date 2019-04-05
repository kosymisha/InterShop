package com.someshop.intershop.controller;

import com.someshop.intershop.model.Advert;
import com.someshop.intershop.model.Shop;
import com.someshop.intershop.model.User;
import com.someshop.intershop.repository.ShopRepository;
import com.someshop.intershop.service.*;

import com.someshop.intershop.service.impl.AdvertServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Controller
public class AdvertController {

    @Autowired
    private AdvertService advertService;

    @Autowired
    private ShopService shopService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @GetMapping("/adverts")
    public String adverts (Model model, @RequestParam(name = "shop", required = false) Shop shop,
                           @AuthenticationPrincipal User user) {
        model.addAttribute("adverts", advertService.findAllAndOrderByShop(shop, user)); //dto
        return "advert/adverts";
    }

    @GetMapping("/adverts/{advert}")
    public String advert (Model model, @PathVariable Advert advert){
        model.addAttribute("advert", advert);
        advertService.addView(advert);
        return "advert/advert";
    }

    @GetMapping("/adverts/create")
    public String advertsCreate (@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("shops", shopService.findByOwner(user)); //dto
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("products", productService.findAll());
        return "advert/create";
    }

    @GetMapping("/adverts/{advert}/delete")
    public String advertDelete (@AuthenticationPrincipal User user, Model model,
                                      @PathVariable Advert advert) {
        advertService.delete(advert, user);
        model.addAttribute("adverts", advertService.findAllAndOrderByShop(null, user)); //dto
        return "redirect:/adverts";
    }

    @PostMapping("/adverts")
    public String advertCreate(
            @RequestParam Map<String, String> form, Model model,
            @RequestParam("photo_url") MultipartFile file) throws IOException {
        Advert advert = advertService.create(form, file);
        model.addAttribute("advert", advert);
        return "redirect:/adverts/" + advert.getId();
    }
}
