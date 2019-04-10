package com.someshop.intershop.service;

import com.someshop.intershop.model.BankCard;
import com.someshop.intershop.model.Order;

public interface BankCardService {
    boolean pay (BankCard card, Order order);
}
