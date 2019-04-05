package com.someshop.intershop.service;

import com.someshop.intershop.dto.AdvertDto;
import com.someshop.intershop.model.Advert;
import com.someshop.intershop.model.Shop;
import com.someshop.intershop.model.User;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface AdvertService {
    void addView (Advert advert);
    Advert create (Map<String, String> form, MultipartFile file) throws IOException;
    Advert create (String storeId, String currency, String price, String productURL, String title, String categoryId, String categoryName, String shop, String photoURL) ;
    void delete (Advert advert, User user);
    List<Advert> findAll();
    List<AdvertDto> findAllAndOrderByShop (Shop shop, User user);
    List<AdvertDto> search (String categoryId, String keyword, String minPrice, String maxPrice, String sort) throws ParserConfigurationException, SAXException, IOException;
    List<Advert> filterByPrice(List<Advert> adverts, String minPrice, String maxPrice);
    List<Advert> sort (List<Advert> adverts, String sortType);
    List<Advert> addWithoutDuplicates(List<Advert> advertsTo, List<Advert> advertsFrom);
}
