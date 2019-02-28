package com.someshop.sneakershop.controller;

import com.someshop.sneakershop.repository.AnnouncementRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class MainController {

    @Autowired
    private AnnouncementRepo announcementRepo;

    @GetMapping("/main")
    public String main(Map<String, Object> model) {
        model.put("announcement", announcementRepo.findAll());
        return "main";
    }

    @GetMapping("/")
    public String slashMain (Map<String, Object> model) {
        model.put("announcement", announcementRepo.findAll());
        return "main";
    }

}
