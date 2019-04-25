package com.someshop.intershop.service;

import com.someshop.intershop.dto.AdvertDto;
import com.someshop.intershop.model.Advert;
import com.someshop.intershop.model.Category;
import com.someshop.intershop.model.Shop;
import com.someshop.intershop.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface AdvertService {
    void addView (Advert advert);
    Advert create (Map<String, String> form, MultipartFile file) throws IOException;
    Advert create (String storeId, String currency, String price, String productURL, String title, Category category, String shop, String photoURL) ;
    Advert findById(String id);
    void delete (Advert advert, User user);
    void setAvailable (Advert advert, User user, Boolean value);
    Page<Advert> getPage(String categoryId, String keyword, String minPrice, String maxPrice, String sort, Integer page, Integer size);
    Page<Advert> findAll(Pageable pageable);
    List<AdvertDto> findAll();
    List<AdvertDto> findByShop (String shopId);
    Page<Advert> search (String categoryId, String keyword, String minPrice, String maxPrice, String sort,
                         Integer page, Integer size) throws ParserConfigurationException, SAXException, IOException;
    Boolean isContainsAdvert (String storeId);
    Page<Advert> findByCriteria(String categoryId, String keyword, String minPrice, String maxPrice, Pageable pageable);
}
