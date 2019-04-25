package com.someshop.intershop.service.impl;

import com.someshop.intershop.dto.AdvertDto;
import com.someshop.intershop.model.Advert;
import com.someshop.intershop.model.Category;
import com.someshop.intershop.model.Role;
import com.someshop.intershop.model.User;
import com.someshop.intershop.repository.AdvertRepository;
import com.someshop.intershop.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class AdvertServiceImpl implements AdvertService {

    @Autowired
    private PriceService priceService;

    @Autowired
    private AdvertRepository advertRepository;

    @Autowired
    private ShopService shopService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private EbayService ebayService;

    @Autowired
    private FileService fileService;


    public void addView (Advert advert) {
        advert.addView();
        advertRepository.save(advert);
    }

    public Advert create (String storeId, String currency, String price, String productURL, String title, Category category,
                          String shop, String photoURL) {
        Map<String, Integer> priceParts = priceService.getParts(price);
        Advert advert = new Advert(storeId,
                currency,
                0,
                "For more information click in URL.",
                productURL,
                title,
                category,
                photoURL,
                true,
                shopService.findByNameShop(shop),
                priceParts.get("intPartPrice"),
                priceParts.get("fractPartPrice"));
        advertRepository.save(advert);
        return advert;
    }

    @Override
    public Advert create (Map<String, String> form, MultipartFile file) throws IOException {
        Map<String, Integer> priceParts = priceService.getParts(form.get("price"));
        Advert advert = new Advert(null,
                "USD",
                0,
                form.get("description"),
                "",
                form.get("title"),
                categoryService.findById(form.get("options")),
                fileService.uploadToS3(file),
                true,
                shopService.findByNameShop(form.get("shop")),
                priceParts.get("intPartPrice"),
                priceParts.get("fractPartPrice"));
        advert.setProductURL(advert.getShop().getUrl() + "/" + advert.getId());
        advertRepository.save(advert);
        return advert;
    }

    @Override
    public Advert findById(String id) {
        return advertRepository.findById(id);
    }

    public void delete (Advert advert, User user) {
        if (user.getRoles().contains(Role.ADMIN) || user.getId().equals(advert.getShop().getOwner().getId())) {
            advertRepository.delete(advert);
        }
    }

    @Override
    public void setAvailable(Advert advert, User user, Boolean value) {
        if(advert.getShop().getOwner().getId().equals(user.getId())) {
            advert.setAvailable(value);
            advertRepository.save(advert);
        }
    }

    @Override
    public List<AdvertDto> findAll() {
        List<AdvertDto> advertDtos = new LinkedList<>();
        for (Advert advert : advertRepository.findAll())
            advertDtos.add(new AdvertDto(advert.getId(), advert.getCurrency(), advert.getViews(),
                    advert.getTitle(), advert.getPhotoURL(), advert.getCategory().getCategoryName(),
                    advert.getShop(), advert.getIntPartPrice(), advert.getFractPartPrice()));
        return advertDtos;
    }

    @Override
    public Page<Advert> findAll(Pageable pageable) {/*
        List<AdvertDto> advertDtos = new LinkedList<>();
        for (Advert advert : advertRepository.findAll(pageable))
            advertDtos.add(new AdvertDto(advert.getId(), advert.getCurrency(), advert.getPrice(), advert.getViews(), advert.getTitle(), advert.getPhotoURL(), advert.getCategory().getCategoryName(), advert.getShop()));
        */
        return advertRepository.findAll(pageable);
    }

    @Override
    public List<AdvertDto> findByShop(String shopId) {
        List<AdvertDto> advertDtos = new LinkedList<>();
        for (Advert advert : advertRepository.findByShop(shopId))
            advertDtos.add(new AdvertDto(advert.getId(), advert.getCurrency(), advert.getViews(),
                    advert.getTitle(), advert.getPhotoURL(), advert.getCategory().getCategoryName(),
                    advert.getShop(), advert.getIntPartPrice(), advert.getFractPartPrice()));
        return advertDtos;
    }

    @Override
    public Page<Advert> search (String categoryId, String keyword, String minPrice, String maxPrice, String sort,
                                Integer page, Integer size) throws ParserConfigurationException, SAXException, IOException {
        ebayService.getItems(keyword, minPrice, maxPrice, categoryId);
        return getPage(categoryId, keyword, minPrice, maxPrice, sort, page, size);
    }

    @Override
    public Page<Advert> getPage(String categoryId, String keyword, String minPrice, String maxPrice, String sort,
                                Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        if (sort.equals("1")) pageable = PageRequest.of(page, size, Sort.Direction.DESC, "views");
        if (sort.equals("2")) pageable = PageRequest.of(page, size, Sort.Direction.ASC, "intPartPrice", "fractPartPrice");
        if (sort.equals("3")) pageable = PageRequest.of(page, size, Sort.Direction.DESC, "intPartPrice", "fractPartPrice");
        return findByCriteria(categoryId, keyword, minPrice, maxPrice, pageable);
    }

    public Page<Advert> findByCriteria(String categoryId, String keyword, String minPrice, String maxPrice, Pageable pageable) {
        return advertRepository.findAll(new Specification<Advert>() {
            @Override
            public Predicate toPredicate(Root<Advert> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                String[] parts;
                if (categoryId != null) predicates.add(criteriaBuilder.equal(root.get("category"), categoryService.findById(categoryId)));
                if (keyword != null) {
                    parts = keyword.split("-");
                    for (String part : parts) predicates.add(criteriaBuilder.like(root.get("title"), "%" + part + "%"));
                }
                if (minPrice.contains(".")){
                    String[] partsPrice = minPrice.split("\\.");
                    predicates.add(criteriaBuilder.or(criteriaBuilder.greaterThan(root.get("intPartPrice"), new Integer(partsPrice[0])),
                            criteriaBuilder.and(criteriaBuilder.equal(root.get("intPartPrice"), new Integer(partsPrice[0])),
                                    criteriaBuilder.greaterThanOrEqualTo(root.get("fractPartPrice"), new Integer(partsPrice[1])))
                            ));
                } else {
                    predicates.add(criteriaBuilder.or(criteriaBuilder.greaterThan(root.get("intPartPrice"), minPrice),
                            criteriaBuilder.and(criteriaBuilder.equal(root.get("intPartPrice"), minPrice),
                                    criteriaBuilder.greaterThanOrEqualTo(root.get("fractPartPrice"), 0))
                    ));
                }
                if (maxPrice.contains(".")){
                    String[] partsPrice = maxPrice.split("\\.");
                    predicates.add(criteriaBuilder.or(criteriaBuilder.lessThan(root.get("intPartPrice"), new Integer(partsPrice[0])),
                            criteriaBuilder.and(criteriaBuilder.equal(root.get("intPartPrice"), new Integer(partsPrice[0])),
                                    criteriaBuilder.lessThanOrEqualTo(root.get("fractPartPrice"), new Integer(partsPrice[1])))
                    ));
                } else {
                    predicates.add(criteriaBuilder.or(criteriaBuilder.lessThan(root.get("intPartPrice"), maxPrice),
                            criteriaBuilder.and(criteriaBuilder.equal(root.get("intPartPrice"), maxPrice),
                                    criteriaBuilder.lessThanOrEqualTo(root.get("fractPartPrice"), 0))
                    ));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        }, pageable);
    }

    @Override
    public Boolean isContainsAdvert(String storeId) {
        return advertRepository.findByStoreId(storeId) != null;
    }
}
