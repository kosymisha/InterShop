package com.someshop.sneakershop.service;

import com.someshop.sneakershop.model.*;
import com.someshop.sneakershop.repository.AnnouncementRepository;
import com.someshop.sneakershop.repository.CategoryRepository;
import com.someshop.sneakershop.repository.ProductRepository;
import com.someshop.sneakershop.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class AnnouncementService {

    @Autowired
    private ProductService productService;

    @Autowired
    private AnnouncementRepository announcementRepository;

    @Autowired
    private ShopRepository shopRepository;

    public void create (Map<String, String> form, MultipartFile file) throws IOException {
        Announcement announcement = new Announcement("USD",
                new Double(form.get("price")), 0, "",
                productService.create(form.get("title"), form.get("category"), file, form.get("description")),
                shopRepository.findByNameShop(form.get("shop")));
        announcement.setProductURL(announcement.getShop().getUrl() + "/" + announcement.getProduct().getId());
        announcementRepository.save(announcement);
    }

    public void delete (Announcement announcement) {
        announcementRepository.delete(announcement);
    }

    public List<Announcement> orderByShop (Shop shop, User user) {
        if(shop != null) return announcementRepository.findByShop(shop);
        else return announcementRepository.findAllOrderByOwner(user);
    }
}
