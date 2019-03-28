package com.someshop.intershop.controller;

import com.someshop.intershop.model.User;
import com.someshop.intershop.repository.AnnouncementRepository;
import com.someshop.intershop.service.CategoryService;
import com.someshop.intershop.service.EbayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class MainController {

    @Autowired
    private AnnouncementRepository announcementRepository;

    @Autowired
    private EbayService ebayService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/")
    public String main(Map<String, Object> model,
                       //@RequestParam(name = "keyword", required = false) String keyword,
                       //@RequestParam(name = "minPrice", required = false) String minPrice,
                       //@RequestParam(name = "maxPrice", required = false) String maxPrice,
                       //@RequestParam(name = "categoryId", required = false) String categoryId,
                       //@RequestParam(name = "sort") String sort,
                       @AuthenticationPrincipal User currentUser) throws Exception {
        model.put("currentUser", currentUser);
        model.put("announcements", announcementRepository.findAll());
        //ebayService.getItems(keyword, minPrice, maxPrice, categoryId);
        model.put("categories", categoryService.findAll());
        return "main";
    }
}