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
import java.math.BigDecimal;
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
    private ShopService shopService;

    public Announcement create (Map<String, String> form, MultipartFile file) throws IOException {
        Announcement announcement = new Announcement(null, "USD",
                new BigDecimal(form.get("price")), 0, "",
                productService.create(form.get("title"), form.get("category"), file, form.get("description")),
                shopService.findByNameShop(form.get("shop")));
        announcement.setProductURL(announcement.getShop().getUrl() + "/" + announcement.getProduct().getId());
        announcementRepository.save(announcement);
        return announcement;
    }

    public void create (String storeId, String currency, String price, String productURL, String title, String categoryId,
                        String categoryName, String shop, String photoURL) {
        if (announcementRepository.findByStoreId(storeId) == null) {
            Announcement announcement = new Announcement(storeId, currency, new BigDecimal(price).setScale(2), 0, productURL,
                    productService.create(title, categoryName, photoURL, "For more information click in URL."),
                    shopService.findByNameShop(shop));
            announcementRepository.save(announcement);
        }
    }

    public void delete (Announcement announcement, User user) {
        if (user.getRoles().contains(Role.ADMIN) || user.getId().equals(announcement.getShop().getOwner().getId())) {
            announcementRepository.delete(announcement);
        }
    }

    public List<Announcement> findAllAndOrderByShop (Shop shop, User user) {
        if(shop != null) return announcementRepository.findByShop(shop);
        else return announcementRepository.findAllOrderByOwner(user);
    }
}
