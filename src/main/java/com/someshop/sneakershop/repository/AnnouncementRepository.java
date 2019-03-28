package com.someshop.sneakershop.repository;

import com.someshop.sneakershop.model.Announcement;
import com.someshop.sneakershop.model.Shop;
import com.someshop.sneakershop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {
    List<Announcement> findByShop (Shop shop);

    @Query(value = "select announcement.id, announcement.store_id, announcement.currency, announcement.price, " +
            "announcement.views, announcement.product_url, announcement.product_id, announcement.shop_id " +
            "from announcement inner join shop on announcement.shop_id = shop.id where user_id = ?1 " +
            "union " +
            "select announcement.id, announcement.store_id, announcement.currency, announcement.price, " +
            "announcement.views, announcement.product_url, announcement.product_id, announcement.shop_id " +
            "from announcement inner join shop on announcement.shop_id = shop.id where user_id != ?1", nativeQuery = true)
    List<Announcement> findAllOrderByOwner(User user);

    Announcement findByStoreId(String storeId);

}
