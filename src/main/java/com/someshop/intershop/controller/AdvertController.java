package com.someshop.intershop.controller;

import com.someshop.intershop.model.Advert;
import com.someshop.intershop.model.Shop;
import com.someshop.intershop.model.User;
import com.someshop.intershop.repository.ShopRepository;
import com.someshop.intershop.service.*;

import com.someshop.intershop.service.impl.AdvertServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.jws.WebParam;
import java.io.IOException;
import java.util.Map;

@Controller
public class AdvertController {

    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private AdvertService advertService;

    @Autowired
    private ShopService shopService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/adverts")
    public String adverts (Model model, @RequestParam(name = "shop") Shop shop) {
        model.addAttribute("adverts", advertService.findByShop(shop.getId().toString()));
        return "advert/adverts";
    }

    @GetMapping("/adverts/{advert}")
    public String advert (Model model, @PathVariable Advert advert){
        model.addAttribute("advert", advert);
        model.addAttribute("usdPrice", currencyService.getUsdValue(advert.getIntPartPrice(), advert.getFractPartPrice()));
        model.addAttribute("eurPrice", currencyService.getEurValueFromUsd(advert.getIntPartPrice(), advert.getFractPartPrice()));
        model.addAttribute("bynPrice", currencyService.getBynValueFromUsd(advert.getIntPartPrice(), advert.getFractPartPrice()));
        advertService.addView(advert);
        return "advert/advert";
    }

    @GetMapping("/adverts/create")
    public String advertsCreate (@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("shops", shopService.findByOwner(user.getId().toString())); //dto
        model.addAttribute("categories", categoryService.findAll());
        return "advert/create";
    }

    @GetMapping("/adverts/{advert}/delete")
    public String advertDelete (@AuthenticationPrincipal User user, Model model,
                                      @PathVariable Advert advert) {
        advertService.delete(advert, user);
        model.addAttribute("adverts", advertService.findAll()); //dto
        return "redirect:/";
    }

    @PostMapping("/adverts")
    public String advertCreate(
            @RequestParam Map<String, String> form, Model model,
            @RequestParam(value = "photo_url", required = false) MultipartFile file) throws IOException {
        Advert advert = advertService.create(form, file);
        model.addAttribute("advert", advert);
        return "redirect:/adverts/" + advert.getId();
    }

    @GetMapping("/adverts/{advert}/available")
    public String setAvailable (@PathVariable Advert advert, @AuthenticationPrincipal User user,
                                @RequestParam(name = "value") Boolean value, Model model) {
        advertService.setAvailable(advert, user, value);
        return "redirect:/adverts/" + advert.getId();
    }
}
