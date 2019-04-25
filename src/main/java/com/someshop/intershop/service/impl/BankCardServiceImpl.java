package com.someshop.intershop.service.impl;

import com.someshop.intershop.model.BankCard;
import com.someshop.intershop.model.Order;
import com.someshop.intershop.model.User;
import com.someshop.intershop.repository.BankCardRepository;
import com.someshop.intershop.repository.OrderRepository;
import com.someshop.intershop.service.BankCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BankCardServiceImpl implements BankCardService {

    @Autowired
    private BankCardRepository bankCardRepository;

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

    @Override
    public BankCard findActiveByUserId(String userId) {
        return bankCardRepository.findActiveByUserId(userId);
    }

    @Override
    public BankCard findById(String id) {
        return bankCardRepository.findById(id);
    }

    @Override
    public List<BankCard> findNonActiveByUserId(String userId) {
        return bankCardRepository.findNonActiveByUserId(userId);
    }

    @Override
    public void makeDefault(User user, BankCard bankCard) {
        BankCard card = bankCardRepository.findActiveByUserId(user.getId().toString());
        card.setActive(false);
        bankCardRepository.save(card);
        card = bankCardRepository.findById(bankCard.getId().toString());
        card.setActive(true);
        bankCardRepository.save(card);
    }

    @Override
    public void delete(BankCard bankCard) {
        bankCardRepository.delete(bankCard);
    }

    @Override
    public void create(Map<String, String> form, User user) {
        bankCardRepository.save(new BankCard(form.get("numberCard"),
                form.get("firstNameCard"),
                form.get("lastNameCard"),
                form.get("monthCard"),
                form.get("yearCard"),
                user, false));
    }

}
