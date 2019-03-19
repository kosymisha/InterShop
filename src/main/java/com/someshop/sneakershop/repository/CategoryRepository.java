package com.someshop.sneakershop.repository;

import com.someshop.sneakershop.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByCategoryName (String categoryName);
}
