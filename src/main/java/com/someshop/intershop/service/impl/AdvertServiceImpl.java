package com.someshop.intershop.service.impl;

import com.someshop.intershop.model.*;
import com.someshop.intershop.repository.AdvertRepository;
import com.someshop.intershop.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

@Service
public class AdvertServiceImpl implements AdvertService {

    @Autowired
    private ProductServiceImpl productService;

    @Autowired
    private AdvertRepository advertRepository;

    @Autowired
    private ShopServiceImpl shopServiceImpl;

    @Autowired
    private CategoryServiceImpl categoryService;

    @Autowired
    private EbayServiceImpl ebayService;

    public void addView (Advert advert) {
        advert.addView();
        advertRepository.save(advert);
    }

    public Advert create (Map<String, String> form, MultipartFile file)
            throws IOException {
        Advert advert = new Advert(null,
                "USD",
                new BigDecimal(form.get("price")),
                0, "",
                productService.create(form.get("title"),
                        categoryService.findById(form.get("options")),
                        file, form.get("description")),
                shopServiceImpl.findByNameShop(form.get("shop")));
        advert.setProductURL(advert.getShop().getUrl() + "/" + advert.getProduct().getId());
        advertRepository.save(advert);
        return advert;
    }

    public Advert create (String storeId, String currency, String price, String productURL, String title, String categoryId,
                          String categoryName, String shop, String photoURL) {
        if (advertRepository.findByStoreId(storeId) == null) {
            Advert advert = new Advert(storeId, currency, new BigDecimal(price).setScale(2), 0, productURL,
                    productService.create(title, categoryService.findByIdAndNameOrCreate(categoryId, categoryName), photoURL, "For more information click in URL."),
                    shopServiceImpl.findByNameShop(shop));
            advertRepository.save(advert);
            return advert;
        } else { return advertRepository.findByStoreId(storeId); }
    }

    public void delete (Advert advert, User user) {
        if (user.getRoles().contains(Role.ADMIN) || user.getId().equals(advert.getShop().getOwner().getId())) {
            advertRepository.delete(advert);
        }
    }

    public List<Advert> findAllAndOrderByShop (Shop shop, User user) {
        if(shop != null) return advertRepository.findByShop(shop);
        else return advertRepository.findAllOrderByOwner(user);
    }

    public List<Advert> search (String categoryId, String keyword, String minPrice, String maxPrice, String sort) throws ParserConfigurationException, SAXException, IOException {
        List<Advert> adverts = new LinkedList<>();
        List<Product> products = productService.findByCriteria(categoryId, keyword);
        for (Product product : products) { adverts.addAll(product.getAdverts()); }
        adverts = addWithoutDuplicates(adverts, ebayService.getItems(keyword, minPrice, maxPrice, categoryId));
        adverts = filterByPrice(adverts, minPrice, maxPrice);
        adverts = sort(adverts, sort);
        return adverts;
    }

    public List<Advert> filterByPrice(List<Advert> adverts, String minPrice, String maxPrice) {
        if (minPrice != null) { adverts.removeIf(advert -> new Double(advert.getPrice().toString()) < new Double(minPrice)); }
        if (maxPrice != null) { adverts.removeIf(advert -> new Double(advert.getPrice().toString()) > new Double(maxPrice)); }
        return adverts;
    }

    public List<Advert> sort (List<Advert> adverts, String sortType) {
        if (sortType.equals("1")){
            Comparator<Advert> comparator = Comparator.comparing(Advert::getViews);
            Collections.sort(adverts, comparator.reversed());
        } else if (sortType.equals("2")) {
            Comparator<Advert> comparator = Comparator.comparing(Advert::getCommentsSize);
            Collections.sort(adverts, comparator.reversed());
        } else if (sortType.equals("3")) {
            Comparator<Advert> comparator = Comparator.comparing(Advert::getPrice);
            Collections.sort(adverts, comparator);
        } else if (sortType.equals("4")) {
            Comparator<Advert> comparator = Comparator.comparing(Advert::getPrice);
            Collections.sort(adverts, comparator.reversed());
        }
        return adverts;
    }

    public List<Advert> addWithoutDuplicates(List<Advert> advertsTo, List<Advert> advertsFrom) {
        for (Advert advert : advertsFrom){
            if (!advertsTo.contains(advert)) {
                advertsTo.add(advert);
            }
        }
        return advertsTo;
    }
}
