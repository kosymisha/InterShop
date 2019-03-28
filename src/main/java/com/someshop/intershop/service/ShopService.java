package com.someshop.intershop.service;

import com.someshop.intershop.model.Role;
import com.someshop.intershop.model.Shop;
import com.someshop.intershop.model.User;
import com.someshop.intershop.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class ShopService {

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private FileService fileService;

    public void create(Map<String, String> form, MultipartFile file, User user) throws IOException {
        shopRepository.save(new Shop(form.get("name_shop"), form.get("url"), user, fileService.upload(file), form.get("description")));
    }

    public void delete(Shop shop, User user) {
        if (user.getRoles().contains(Role.ADMIN) || user.getId().equals(shop.getOwner().getId())){
            shopRepository.delete(shop);
        }
    }

    public Shop findByNameShop (String shop) {
        return shopRepository.findByNameShop(shop);
    }
}
