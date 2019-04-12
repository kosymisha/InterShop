package com.someshop.intershop.service.impl;

import com.someshop.intershop.dto.ShopDto;
import com.someshop.intershop.model.Role;
import com.someshop.intershop.model.Shop;
import com.someshop.intershop.model.User;
import com.someshop.intershop.repository.ShopRepository;
import com.someshop.intershop.service.ShopService;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private FileServiceImpl fileService;

    public Shop create(Map<String, String> form, MultipartFile file, User user) throws IOException {
        if (shopRepository.findByNameShop(form.get("shopName")) == null) {
            Shop shop = new Shop(form.get("shopName"),
                    form.get("shopUrl"),
                    user,
                    fileService.uploadToS3(file),
                    //fileService.uploadLocal(file),
                    form.get("description"));
            shopRepository.save(shop);
            return shop;
        } else {
            return null;
        }
    }

    public void delete(Shop shop, User user) {
        if (user.getRoles().contains(Role.ADMIN) || user.getId().equals(shop.getOwner().getId())){
            shopRepository.delete(shop);
        }
    }

    @Override
    public Shop saveInfo(Shop shop, User user, Map<String, String> form, MultipartFile file) {
        Shop shopFromBd = shopRepository.findByNameShop(form.get("shopName"));
        if ((shopFromBd == null || shopFromBd.getId().equals(shop.getId())) &&
                shop.getOwner().getId().equals(user.getId())) {
            if (!file.isEmpty()) {
                try {
                    //delete old photo from s3
                    shop.setPhotoURL(fileService.uploadToS3(file));
                } catch (IOException e) { e.printStackTrace(); }
            }
            shop.setNameShop(form.get("shopName"));
            shop.setUrl(form.get("shopUrl"));
            shop.setDescription(form.get("description"));
            shopRepository.save(shop);
            return shop;
        } else return null;
    }

    public Shop findByNameShop (String shop) {
        return shopRepository.findByNameShop(shop);
    }

    @Override
    public List<ShopDto> findByOwner(String id) {
        List<ShopDto> shopDtos = new LinkedList<>();
        for (Shop shop : shopRepository.findByOwner(id)) {
            shopDtos.add(new ShopDto(shop.getNameShop()));
        }
        return shopDtos;
    }

    @Override
    public List<ShopDto> findAllOrderByOwner(User user) {
        List<ShopDto> shopShortDtos = new LinkedList<>();
        for (Shop shop : shopRepository.findAllOrderByOwner(user)){
            shopShortDtos.add(new ShopDto(shop.getId(), shop.getNameShop(), shop.getPhotoURL(), shop.getOwner()));
        }
        return shopShortDtos;
    }

}
