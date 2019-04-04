package com.someshop.intershop.service;

import com.someshop.intershop.model.Shop;
import com.someshop.intershop.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

public interface ShopService {
    void create(Map<String, String> form, MultipartFile file, User user) throws IOException;
    void delete(Shop shop, User user);
    Shop findByNameShop (String shop);
}
