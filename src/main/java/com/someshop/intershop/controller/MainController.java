package com.someshop.intershop.controller;

import com.someshop.intershop.model.User;
import com.someshop.intershop.repository.AnnouncementRepository;
import com.someshop.intershop.service.CategoryService;
import com.someshop.intershop.service.EbayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
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
    public String main(Map<String, Object> model, @AuthenticationPrincipal User currentUser) throws Exception {
        model.put("currentUser", currentUser);
        model.put("announcements", announcementRepository.findAll());
        model.put("categories", categoryService.findAll());
        return "main";
    }

    @GetMapping("/search")
    public String search(@RequestParam(name = "sort") String sort,
                         @RequestParam(name = "categoryId", required = false) String categoryId,
                         @RequestParam(name = "keyword", required = false) String keyword,
                         @RequestParam(name = "minPrice", required = false) String minPrice,
                         @RequestParam(name = "maxPrice", required = false) String maxPrice,
                         Model model) throws ParserConfigurationException, SAXException, IOException {
        model.addAttribute("adverts", ebayService.getItems(keyword, minPrice, maxPrice, categoryId));
        //model.addAttribute("adverts", announcementRepository.findAll());
        return "parts/advertsMain";
    }
}
