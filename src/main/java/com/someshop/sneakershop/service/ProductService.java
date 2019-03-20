package com.someshop.sneakershop.service;

import com.someshop.sneakershop.model.Category;
import com.someshop.sneakershop.model.Product;
import com.someshop.sneakershop.repository.CategoryRepository;
import com.someshop.sneakershop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private FileService fileService;

    public Product create(String title, String category, MultipartFile file, String description) throws IOException {
        Product product = new Product(title,
                categoryRepository.findByCategoryName(category),
                fileService.upload(file),
                description);
        productRepository.save(product);
        return product;
    }

}
