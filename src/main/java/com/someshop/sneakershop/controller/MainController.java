package com.someshop.sneakershop.controller;

import com.someshop.sneakershop.repository.AnnouncementRepo;
import com.someshop.sneakershop.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class MainController {

    @Autowired
    private AnnouncementRepo announcementRepo;

    @Autowired
    private RequestService ebayService;

    @GetMapping("/main")
    public String main(Map<String, Object> model,
                        @RequestParam(name="keyword", required = false) String keyword
    ) throws Exception {
        model.put("announcements", announcementRepo.findAll());
        model.put("announcementsEbay", ebayService.getItems(keyword));

        return "main";
    }

    @GetMapping("/")
    public String slashMain (Map<String, Object> model) {
        model.put("announcements", announcementRepo.findAll());
        model.put("announcementsEbay", null);
        return "main";
    }

}
