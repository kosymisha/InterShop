package com.someshop.intershop.service;

import com.someshop.intershop.model.Advert;
import com.someshop.intershop.model.Order;
import com.someshop.intershop.model.User;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface OrderService {
    void createOrder (User user, Advert advert);
    Boolean payOrder (Order order, User user, String cardId);
    boolean deleteOrder (Order order, User user);
    List<Order> getOrdersByUserIdNotPaid (String id);
    List<Order> getOrdersByUserIdPaid (String id);
    List<Order> findAllPaidBySeller (String idSeller);
    List<Order> findAllPaidByShop (String idShop);
    Map<String, Integer> sumPrice (List<Order> orders);
}
