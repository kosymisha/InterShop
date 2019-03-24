package com.someshop.sneakershop.controller;

import com.someshop.sneakershop.model.Announcement;
import com.someshop.sneakershop.model.Comment;
import com.someshop.sneakershop.model.Shop;
import com.someshop.sneakershop.model.User;
import com.someshop.sneakershop.repository.AnnouncementRepository;
import com.someshop.sneakershop.repository.CommentRepository;
import com.someshop.sneakershop.repository.ShopRepository;
import com.someshop.sneakershop.service.AnnouncementService;
import com.someshop.sneakershop.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.jws.WebParam;
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
        model.addAttribute("announcements", announcementService.orderByShop(null, user));
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
