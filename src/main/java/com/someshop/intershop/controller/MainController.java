package com.someshop.intershop.controller;

import com.someshop.intershop.repository.CategoryRepository;
import com.someshop.intershop.service.AdvertService;
import com.someshop.intershop.service.CurrencyService;
import com.someshop.intershop.service.XmlRequestService;
import com.someshop.intershop.service.impl.CategoryServiceImpl;
import com.someshop.intershop.service.impl.SearchServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class MainController {

    @Autowired
    private AdvertService advertService;

    @Autowired
    private CategoryServiceImpl categoryService;

    @Autowired
    private SearchServiceImpl searchService;

    @Autowired
    private XmlRequestService xmlRequestService;

    @GetMapping("/")
    public String main(Model model, HttpSession session) {
        model.addAttribute("session", session.getId());
        model.addAttribute("categories", categoryService.findAll());
        return "main";
    }

    @GetMapping("/search")
    public String search(@RequestParam(name = "sort") String sort,
                         @RequestParam(name = "categoryId", required = false) String categoryId,
                         @RequestParam(name = "keyword", required = false) String keyword,
                         @RequestParam(name = "minPrice", defaultValue = "0") String minPrice,
                         @RequestParam(name = "maxPrice", defaultValue = "1000000000") String maxPrice,
                         @RequestParam(name = "page", defaultValue = "0") Integer page,
                         @RequestParam(name = "size", defaultValue = "10") Integer size,
                         Model model, HttpServletRequest httpServletRequest) {
        model.addAttribute("page", searchService.search(categoryId, keyword, minPrice, maxPrice, sort, page, size));
        model.addAttribute("url", "/pagination?" + xmlRequestService.filterQueryString(httpServletRequest.getQueryString()));
        return "parts/advertsMain";
    }

    @GetMapping("/pagination")
    public String getPage (@RequestParam(name = "sort") String sort,
                           @RequestParam(name = "categoryId", required = false) String categoryId,
                           @RequestParam(name = "keyword", required = false) String keyword,
                           @RequestParam(name = "minPrice", defaultValue = "0") String minPrice,
                           @RequestParam(name = "maxPrice", defaultValue = "1000000000") String maxPrice,
                           @RequestParam(name = "page", defaultValue = "0") Integer page,
                           @RequestParam(name = "size", defaultValue = "10") Integer size,
                           Model model, HttpServletRequest httpServletRequest) {
        model.addAttribute("page", advertService.getPage(categoryId, keyword, minPrice, maxPrice, sort, page, size));
        model.addAttribute("url", "/pagination?" + xmlRequestService.filterQueryString(httpServletRequest.getQueryString()));
        return "parts/advertsMain";
    }
}
