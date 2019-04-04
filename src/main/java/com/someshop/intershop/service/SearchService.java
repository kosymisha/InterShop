package com.someshop.intershop.service;

import com.someshop.intershop.model.Advert;

import java.util.List;

public interface SearchService {
    List<Advert> search(String categoryId, String keyword, String minPrice, String maxPrice, String sort);
}
