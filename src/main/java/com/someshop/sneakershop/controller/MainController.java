package com.someshop.sneakershop.controller;

import com.someshop.sneakershop.model.User;
import com.someshop.sneakershop.repository.AnnouncementRepo;
import com.someshop.sneakershop.service.EbayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class MainController {

    @Autowired
    private AnnouncementRepo announcementRepo;

    @Autowired
    private EbayService ebayService;

    @GetMapping("/main")
    public String main(Map<String, Object> model,
                       @RequestParam(name = "keyword", required = false) String keyword,
                       @RequestParam(name = "minPrice", required = false) String minPrice,
                       @RequestParam(name = "maxPrice", required = false) String maxPrice,
                       @RequestParam(name = "categoryId", required = false) String categoryId,
                       @AuthenticationPrincipal User currentUser
    ) throws Exception {
        model.put("currentUser", currentUser);
        model.put("announcements", announcementRepo.findAll());
        model.put("announcementsEbay", ebayService.getItems(keyword, minPrice, maxPrice, categoryId));

        return "main";
    }

}
