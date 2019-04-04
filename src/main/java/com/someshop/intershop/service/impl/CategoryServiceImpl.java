package com.someshop.intershop.service.impl;

import com.someshop.intershop.model.Category;
import com.someshop.intershop.repository.CategoryRepository;
import com.someshop.intershop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public Category findByIdAndNameOrCreate (String id, String name) {
        if (categoryRepository.findById(id) == null &&
                categoryRepository.findByCategoryName(name) == null) {
            Category category = new Category(new Long(id), name);
            categoryRepository.save(category);
            return category;
        } else {
            return categoryRepository.findById(id.toString());
        }
    }

    public Category findByCategoryName (String name) {
        return  categoryRepository.findByCategoryName(name);
    }

    public List<Category> findAll () {
        return categoryRepository.findAll();
    }

    public Category findById (String id) {
        return categoryRepository.findById(id);
    }
}
