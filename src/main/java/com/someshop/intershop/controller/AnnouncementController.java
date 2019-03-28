package com.someshop.intershop.controller;

import com.someshop.intershop.model.Announcement;
import com.someshop.intershop.model.Shop;
import com.someshop.intershop.model.User;
import com.someshop.intershop.repository.ShopRepository;
import com.someshop.intershop.service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
        model.addAttribute("announcements", announcementService.findAllAndOrderByShop(shop, user));
        model.addAttribute("user", user);
        return "announcement/announcements";
    }

    @GetMapping("/announcements/{announcement}")
    public String announcement (Model model, @PathVariable Announcement announcement,
                                @AuthenticationPrincipal User user){
        model.addAttribute("announcement", announcement);
        model.addAttribute("user", user);
        return "announcement/announcement";
    }

    @GetMapping("/announcements/create")
    public String announcementsCreate (@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("shops", shopRepository.findByOwner(user));
        return "announcement/create";
    }

    @GetMapping("/announcements/{announcement}/delete")
    public String announcementDelete (@AuthenticationPrincipal User user, Model model,
                                      @PathVariable Announcement announcement) {
        announcementService.delete(announcement, user);
        model.addAttribute("announcements", announcementService.findAllAndOrderByShop(null, user));
        model.addAttribute("user", user);
        return "announcement/announcements";
    }

    @PostMapping("/announcements")
    public String announcementCreate(
            @RequestParam Map<String, String> form, Model model,
            @AuthenticationPrincipal User user,
            @RequestParam("photo_url") MultipartFile file) throws IOException {
        Announcement announcement = announcementService.create(form, file);
        model.addAttribute("announcement", announcement);
        model.addAttribute("user", user);
        return "redirect:/announcements/" + announcement.getId();
    }
}
