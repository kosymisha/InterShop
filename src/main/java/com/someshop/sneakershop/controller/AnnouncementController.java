package com.someshop.sneakershop.controller;

import com.someshop.sneakershop.model.Announcement;
import com.someshop.sneakershop.model.Shop;
import com.someshop.sneakershop.model.User;
import com.someshop.sneakershop.repository.AnnouncementRepository;
import com.someshop.sneakershop.repository.ShopRepository;
import com.someshop.sneakershop.service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class AnnouncementController {

    @Autowired
    private AnnouncementService announcementService;

    @Autowired
    private ShopRepository shopRepository;

    @GetMapping("/announcements")
    public String announcements (Model model, @RequestParam(name = "shop", required = false) Shop shop,
                                 @AuthenticationPrincipal User user) {
        model.addAttribute("announcements", announcementService.orderByShop(shop, user));
        model.addAttribute("user", user);
        return "announcement/announcements";
    }

    @GetMapping("/announcements/{announcement}")
    public String announcement (Model model, @PathVariable Announcement announcement,
                                @AuthenticationPrincipal User user){
        model.addAttribute("announcement", announcement);
        return "announcement/announcement";
    }

    @GetMapping("/announcements/new")
    public String announcementsCreate (@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("shops", shopRepository.findByOwner(user));
        return "announcement/announcementCreate";
    }

    @PostMapping("/announcements")
    public String announcementCreate(
            @RequestParam Map<String, String> form,
            @RequestParam("photo_url") MultipartFile file) throws IOException {
        announcementService.create(form, file);
        return "announcement/announcementCreate";
    }
}
