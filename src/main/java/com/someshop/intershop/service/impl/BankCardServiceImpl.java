package com.someshop.intershop.service.impl;

import com.someshop.intershop.model.BankCard;
import com.someshop.intershop.model.Order;
import com.someshop.intershop.repository.OrderRepository;
import com.someshop.intershop.service.BankCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankCardServiceImpl implements BankCardService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public boolean pay(BankCard card, Order order) {
        if((new Integer(card.getMonth()) + (int)(Math.random() * 50) + 1) % 2 == 0) {
            order.setPaid(true);
            orderRepository.save(order);
            return true;
        } else { return true; }
    }

}
