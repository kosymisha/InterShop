package com.someshop.sneakershop.controller;

import com.someshop.sneakershop.model.*;
import com.someshop.sneakershop.repository.AnnouncementRepository;
import com.someshop.sneakershop.repository.CategoryRepository;
import com.someshop.sneakershop.repository.ProductRepository;
import com.someshop.sneakershop.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
public class AnnouncementController {

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private AnnouncementRepository announcementRepository;

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/announcements")
    public String announcements (Model model, @RequestParam(name = "shop", required = false) Shop shop,
                                 @AuthenticationPrincipal User user) {
        if(shop != null) model.addAttribute("announcements", announcementRepository.findByShop(shop));
        else model.addAttribute("announcements", announcementRepository.findAllOrderByOwner(user));
        model.addAttribute("user", user);
        return "announcement/announcements";
    }

    @GetMapping("/announcements/{announcement}")
    public String announcement (Model model, @PathVariable Announcement announcement,
                                @AuthenticationPrincipal User user){
        model.addAttribute("announcement", announcement);
        return "announcement/announcement";
    }

    @GetMapping("/announcements/new")
    public String announcementsCreate (@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("shops", shopRepository.findByOwner(user));
        return "announcement/announcementCreate";
    }

    @PostMapping("/announcements")
    public String announcementCreate(
            @AuthenticationPrincipal User user,
            @RequestParam(name = "category") String category,
            @RequestParam(name = "title") String title,
            @RequestParam(name = "product_url") String product_url,
            @RequestParam(name = "description") String description,
            @RequestParam(name = "price") String price,
            @RequestParam(name = "shop") String shop,
            @RequestParam("photo_url") MultipartFile file) throws IOException {

        Product product = new Product(title, categoryRepository.findByCategoryName(category), "temp", description);

        if(file != null){
            File uploadDir = new File(uploadPath);
            if(!uploadDir.exists()){
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file.getOriginalFilename();
            file.transferTo(new File(uploadPath + "/" + resultFileName));
            product.setPhotoURL(resultFileName);
        }
        productRepository.save(product);
        Announcement announcement = new Announcement("USD", new Double(price), 0,
                "www.com", product, shopRepository.findByNameShop(shop));
        announcementRepository.save(announcement);
        return "announcement/announcementCreate";
    }
}
