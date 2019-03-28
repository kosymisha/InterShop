package com.someshop.sneakershop.repository;

import com.someshop.sneakershop.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByCategoryName (String categoryName);

    @Query(value = "select * from category where id = ?1", nativeQuery = true)
    Category findById (String id);
}
