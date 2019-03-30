package com.someshop.intershop.service;

import com.someshop.intershop.model.Category;
import com.someshop.intershop.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public Category findByIdOrCreate (String id, String name) {
        Category categoryFromDb = categoryRepository.findById(id);
        if (categoryFromDb == null) {
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

    public List<Category> findAllByKeyword (String keyword) {
        List<Category> categories = categoryRepository.findAll();

        return categories;
    }
}
