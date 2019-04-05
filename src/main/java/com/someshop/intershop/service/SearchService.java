package com.someshop.intershop.service;

import com.someshop.intershop.dto.AdvertDto;

import java.util.List;

public interface SearchService {
    List<AdvertDto> search(String categoryId, String keyword, String minPrice, String maxPrice, String sort);
}
