package com.someshop.intershop.repository;

import com.someshop.intershop.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(value = "select * from orders where user_id = ?1 and paid = 0", nativeQuery = true)
    List<Order> findAllByUserIdAndNotPaid(String id);

    @Query(value = "select * from orders where user_id = ?1 and paid = 1", nativeQuery = true)
    List<Order> findAllByUserIdAndPaid(String id);
}
