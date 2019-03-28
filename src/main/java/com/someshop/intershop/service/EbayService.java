package com.someshop.intershop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;


import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URL;

@Service
public class EbayService {

    @Autowired
    XmlRequestService xmlRequestService;

    @Value("${ebayUrlPattern}")
    private String urlPattern;

    public void getItems(String keyword, String minPrice, String maxPrice, String categoryId ) throws IOException, ParserConfigurationException, SAXException {
        if (keyword != "" && keyword != null) { urlPattern += "&keywords=" + keyword.replace(" ", "-"); }
        if (minPrice != null) { urlPattern += "&itemFilter(0).name=MinPrice&itemFilter(0).value=" + minPrice; }
        if (maxPrice != null) { urlPattern += "&itemFilter(1).name=MaxPrice&itemFilter(1).value=" + maxPrice; }
        if (categoryId != null) { urlPattern += "&categoryId=" + categoryId; }
        xmlRequestService.send(new URL(urlPattern), "GET");
    }
}