package com.someshop.intershop.service;

import com.someshop.intershop.model.Advert;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SearchService {
    Page<Advert> search(String categoryId, String keyword, String minPrice, String maxPrice, String sort, Integer page, Integer size);
}
