package com.someshop.intershop.service.impl;

import com.someshop.intershop.model.Advert;
import com.someshop.intershop.service.SearchService;
import com.someshop.intershop.service.impl.AdvertServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private AdvertServiceImpl advertServiceImpl;

    @Override
    public List<Advert> search(String categoryId, String keyword, String minPrice, String maxPrice, String sort) {
        try {
            return advertServiceImpl.search(categoryId, keyword, minPrice, maxPrice, sort);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace(); return null;
        }
    }
}
