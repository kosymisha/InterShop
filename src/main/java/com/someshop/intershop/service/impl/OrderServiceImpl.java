package com.someshop.intershop.service.impl;

import com.someshop.intershop.model.Advert;
import com.someshop.intershop.model.BankCard;
import com.someshop.intershop.model.Order;
import com.someshop.intershop.model.User;
import com.someshop.intershop.repository.AdvertRepository;
import com.someshop.intershop.repository.OrderRepository;
import com.someshop.intershop.service.BankCardService;
import com.someshop.intershop.service.OrderService;
import com.someshop.intershop.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private PriceService priceService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private BankCardService bankCardService;

    @Autowired
    private AdvertRepository advertRepository;

    @Override
    public void createOrder(User user, Advert advert) {
        if (advert.getAvailable()) {
            orderRepository.save(new Order(false, user, advert));
            advert.setAvailable(false);
            advertRepository.save(advert);
        }
    }

    @Override
    public Boolean payOrder(Order order, User user, String cardId) {
        if (order.getUser().getId().equals(user.getId()) &&
                bankCardService.pay(bankCardService.findById(cardId), order)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteOrder(Order order, User user) {
        if (order.getUser().getId().equals(user.getId())) {
            orderRepository.delete(order);
            order.getAdvert().setAvailable(true);
            advertRepository.save(order.getAdvert());
            return true;
        } else { return false; }
    }

    @Override
    public List<Order> getOrdersByUserIdNotPaid(String id) {
        return orderRepository.findAllByUserIdAndNotPaid(id);
    }

    @Override
    public List<Order> getOrdersByUserIdPaid(String id) {
        return orderRepository.findAllByUserIdAndPaid(id);
    }

    @Override
    public List<Order> findAllPaidBySeller(String idSeller) {
        return orderRepository.findAllPaidBySeller(idSeller);
    }

    @Override
    public List<Order> findAllPaidByShop(String idShop) {
        return orderRepository.findAllPaidByShop(idShop);
    }

    @Override
    public Map<String, Integer> sumPrice(List<Order> orders) {
        BigDecimal sum = new BigDecimal(0).setScale(2, RoundingMode.FLOOR);
        for (Order order : orders) {
            sum = sum.add(new BigDecimal(order.getAdvert().getIntPartPrice().toString() +
                    "." + order.getAdvert().getFractPartPrice().toString()));
        }
        return priceService.getParts(sum.toString());
    }


}
