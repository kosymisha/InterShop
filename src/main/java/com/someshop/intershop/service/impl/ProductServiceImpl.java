package com.someshop.intershop.service.impl;

import com.someshop.intershop.model.Category;
import com.someshop.intershop.model.Product;
import com.someshop.intershop.repository.ProductRepository;
import com.someshop.intershop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryServiceImpl categoryService;

    @Autowired
    private FileServiceImpl fileServiceImpl;

    public Product create(String title, Category category, MultipartFile file) throws IOException {
        Product product = new Product(title,
                category,
                fileServiceImpl.uploadToS3(file));
        productRepository.save(product);
        return product;
    }

    public Product create(String title, Category category, String photoURL) {
        Product product = new Product(title,
                category,
                photoURL);
        productRepository.save(product);
        return product;
    }

    public List<Product> findAll() {
        return productRepository.findAll(new Specification<Product>() {
            @Override
            public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return null;
            }
        });
    }

    public List<Product> findByCriteria(String categoryId, String keyword) {
        return productRepository.findAll(new Specification<Product>() {
            @Override
            public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if (categoryId != null) { predicates.add(criteriaBuilder.equal(root.get("category"), categoryService.findById(categoryId))); }
                String[] parts;
                if (keyword != null) {
                    parts = keyword.split("-");
                    for (String part : parts) {
                        predicates.add(criteriaBuilder.like(root.get("title"), "%" + part + "%"));
                    }
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        });
    }

}
