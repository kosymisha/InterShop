package com.someshop.intershop.repository;

import com.someshop.intershop.model.Advert;
import com.someshop.intershop.model.Shop;
import com.someshop.intershop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdvertRepository extends JpaRepository<Advert, Long>, JpaSpecificationExecutor<Advert> {

    List<Advert> findByShop (Shop shop);

    @Query(value = "select advert.id, advert.store_id, advert.currency, advert.price, " +
            "advert.views, advert.product_url, advert.product_id, advert.shop_id " +
            "from advert inner join shop on advert.shop_id = shop.id where user_id = ?1 " +
            "union " +
            "select advert.id, advert.store_id, advert.currency, advert.price, " +
            "advert.views, advert.product_url, advert.product_id, advert.shop_id " +
            "from advert inner join shop on advert.shop_id = shop.id where user_id != ?1", nativeQuery = true)
    List<Advert> findAllOrderByOwner(User user);

    Advert findByStoreId(String storeId);

}
