package com.someshop.intershop.service.impl;

import com.someshop.intershop.dto.AdvertDto;
import com.someshop.intershop.model.Advert;
import com.someshop.intershop.service.AdvertService;
import com.someshop.intershop.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private AdvertService advertService;

    @Override
    public Page<Advert> search(String categoryId, String keyword, String minPrice, String maxPrice, String sort,
                               Integer page, Integer size) {
        try {
            return advertService.search(categoryId, keyword, minPrice, maxPrice, sort, page, size);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace(); return null;
        }
    }
}
