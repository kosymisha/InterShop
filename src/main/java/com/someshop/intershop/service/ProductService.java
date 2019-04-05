package com.someshop.intershop.service;

import com.someshop.intershop.model.Category;
import com.someshop.intershop.model.Product;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    Product create(String title, Category category, MultipartFile file) throws IOException;
    Product create(String title, Category category, String photoURL);
    List<Product> findAll();
    List<Product> findByCriteria(String categoryId, String keyword);

}
