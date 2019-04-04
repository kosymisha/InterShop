package com.someshop.intershop.controller;

import com.someshop.intershop.model.Advert;
import com.someshop.intershop.model.Shop;
import com.someshop.intershop.model.User;
import com.someshop.intershop.repository.ShopRepository;
import com.someshop.intershop.service.impl.AdvertServiceImpl;
import com.someshop.intershop.service.CategoryService;
import com.someshop.intershop.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private AdvertServiceImpl advertServiceImpl;

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/adverts")
    public String adverts (Model model, @RequestParam(name = "shop", required = false) Shop shop,
                           @AuthenticationPrincipal User user) {
        model.addAttribute("adverts", advertServiceImpl.findAllAndOrderByShop(shop, user));
        return "advert/adverts";
    }

    @GetMapping("/adverts/{advert}")
    public String advert (Model model, @PathVariable Advert advert){
        model.addAttribute("advert", advert);
        model.addAttribute("object", advert);
        model.addAttribute("comments", commentService.findAllByAdvert(advert));
        model.addAttribute("object", advert);
        advertServiceImpl.addView(advert);
        return "advert/advert";
    }

    @GetMapping("/adverts/create")
    public String advertsCreate (@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("shops", shopRepository.findByOwner(user));
        model.addAttribute("categories", categoryService.findAll());
        return "advert/create";
    }

    @GetMapping("/adverts/{advert}/delete")
    public String advertDelete (@AuthenticationPrincipal User user, Model model,
                                      @PathVariable Advert advert) {
        advertServiceImpl.delete(advert, user);
        model.addAttribute("adverts", advertServiceImpl.findAllAndOrderByShop(null, user));
        model.addAttribute("user", user);
        return "advert/adverts";
    }

    @PostMapping("/adverts")
    public String advertCreate(
            @RequestParam Map<String, String> form, Model model,
            @AuthenticationPrincipal User user,
            @RequestParam("photo_url") MultipartFile file) throws IOException {
        Advert advert = advertServiceImpl.create(form, file);
        model.addAttribute("advert", advert);
        model.addAttribute("user", user);
        return "redirect:/adverts/" + advert.getId();
    }
}
