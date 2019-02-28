package com.someshop.sneakershop.repository;

import com.someshop.sneakershop.model.Sneaker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SneakerRepo extends JpaRepository<Sneaker, Long> {

}
