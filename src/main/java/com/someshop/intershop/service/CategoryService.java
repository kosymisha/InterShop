package com.someshop.intershop.service;

import com.someshop.intershop.model.Category;

import java.util.List;

public interface CategoryService {
    Category findByIdAndNameOrCreate (String id, String name);
    Category findByCategoryName (String name);
    List<Category> findAll ();
    Category findById (String id);
}
