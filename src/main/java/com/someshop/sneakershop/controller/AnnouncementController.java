package com.someshop.sneakershop.controller;

import com.someshop.sneakershop.model.User;
import com.someshop.sneakershop.repository.AnnouncementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AnnouncementController {

    @Autowired
    private AnnouncementRepository announcementRepository;

    @GetMapping("/announcements")
    public String announcements (Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("announcements", announcementRepository.findAll());
        return "announcements";
    }

}
