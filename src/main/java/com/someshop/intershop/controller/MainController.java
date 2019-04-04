package com.someshop.intershop.controller;

import com.someshop.intershop.model.User;
import com.someshop.intershop.repository.AdvertRepository;
import com.someshop.intershop.service.CategoryService;
import com.someshop.intershop.service.impl.CategoryServiceImpl;
import com.someshop.intershop.service.impl.SearchServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class MainController {

    @Autowired
    private AdvertRepository advertRepository;

    @Autowired
    private CategoryServiceImpl categoryService;

    @Autowired
    private SearchServiceImpl searchService;

    @GetMapping("/")
    public String main(Map<String, Object> model, @AuthenticationPrincipal User currentUser) throws Exception {
        model.put("currentUser", currentUser);
        model.put("adverts", advertRepository.findAll());
        model.put("categories", categoryService.findAll());
        return "main";
    }

    @GetMapping("/search")
    public String search(@RequestParam(name = "sort") String sort,
                         @RequestParam(name = "categoryId", required = false) String categoryId,
                         @RequestParam(name = "keyword", required = false) String keyword,
                         @RequestParam(name = "minPrice", required = false) String minPrice,
                         @RequestParam(name = "maxPrice", required = false) String maxPrice,
                         Model model) {
        model.addAttribute("adverts", searchService.search(categoryId, keyword, minPrice, maxPrice, sort));
        //model.addAttribute("adverts", ebayService.getItems(keyword, minPrice, maxPrice, categoryId));
        //model.addAttribute("adverts", advertRepository.findAll());
        return "parts/advertsMain";
    }
}
