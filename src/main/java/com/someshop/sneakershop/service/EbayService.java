package com.someshop.sneakershop.service;

import com.someshop.sneakershop.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

@Service
public class EbayService {

    @Autowired
    XmlRequestService xmlRequestService;

    @Value("${ebayUrlPattern}")
    private String urlPattern;

    public List getItems(String keyword, String minPrice, String maxPrice, String categoryId ) throws MalformedURLException {
            if (keyword != "" && keyword != null) { urlPattern += "&keywords=" + keyword.replace(" ", "-"); }
            if (minPrice != null) { urlPattern += "&itemFilter(0).name=MinPrice&itemFilter(0).value=" + minPrice; }
            if (maxPrice != null) { urlPattern += "&itemFilter(1).name=MaxPrice&itemFilter(1).value=" + maxPrice; }
            if (categoryId != null) { urlPattern += "&categoryId=" + categoryId; }
        return xmlRequestService.getItemsByUrl(new URL(urlPattern));
    }
}