package com.someshop.intershop.repository;

import com.someshop.intershop.model.Shop;
import com.someshop.intershop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {

    List<Shop> findByOwner(User user);

    @Query(value = "select * from shop where shop.user_id = ?1 union all select * from shop where shop.user_id != ?1", nativeQuery = true)
    List<Shop> findAllOrderByOwner(User user);

    void delete (Shop shop);

    Shop findByNameShop(String nameShop);
}
