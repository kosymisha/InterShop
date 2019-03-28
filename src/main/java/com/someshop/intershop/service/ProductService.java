package com.someshop.intershop.service;

import com.someshop.intershop.model.Product;
import com.someshop.intershop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private FileService fileService;

    public Product create(String title, String category, MultipartFile file, String description) throws IOException {
        Product product = new Product(title,
                category,
                fileService.upload(file),
                description);
        productRepository.save(product);
        return product;
    }

    public Product create(String title, String categoryName, String photoURL, String description) {
        Product product = new Product(title,
                categoryName,
                photoURL,
                description);
        productRepository.save(product);
        return product;
    }

}
