package com.someshop.intershop.service;

import com.someshop.intershop.model.Advert;
import com.someshop.intershop.model.Order;
import com.someshop.intershop.model.User;

import java.math.BigDecimal;
import java.util.List;

public interface OrderService {
    void createOrder (User user, Advert advert);
    boolean payOrder (Order order, User user);
    boolean deleteOrder (Order order, User user);
    List<Order> getOrdersByUserIdNotPaid (String id);
    List<Order> getOrdersByUserIdPaid (String id);
    List<Order> findAllPaidBySeller (String idSeller);
    List<Order> findAllPaidByShop (String idShop);
    BigDecimal sumPrice (List<Order> orders);
}
