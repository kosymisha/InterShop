package com.someshop.sneakershop.repository;

import com.someshop.sneakershop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
