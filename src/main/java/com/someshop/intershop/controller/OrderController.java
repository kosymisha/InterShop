package com.someshop.intershop.controller;

import com.someshop.intershop.dto.CurrencyDto;
import com.someshop.intershop.model.Order;
import com.someshop.intershop.model.User;
import com.someshop.intershop.service.*;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Controller
public class OrderController {

    @Autowired
    private PriceService priceService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private AdvertService advertService;

    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private BankCardService bankCardService;

    @GetMapping("/orders")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String orders (Model model, @RequestParam(name = "userId", required = false) String userId) {
        model.addAttribute("orders", orderService.getOrdersByUserIdNotPaid(userId));
        model.addAttribute("paidOrders", orderService.getOrdersByUserIdPaid(userId));
        return "order/orders";
    }

    @GetMapping("/orders/my")
    @PreAuthorize("hasAuthority('USER')")
    public String myOrders (Model model, @AuthenticationPrincipal User user) {
        List<Order> orders = orderService.getOrdersByUserIdPaid(user.getId().toString());
        Map<String, Integer> sumPrice = orderService.sumPrice(orders);
        model.addAttribute("orders", orderService.getOrdersByUserIdNotPaid(user.getId().toString()));
        model.addAttribute("paidOrders", orders);
        model.addAttribute("itemCount", orders.size());
        model.addAttribute("totalUsd", new CurrencyDto(priceService.getPrice(sumPrice), "USD"));
        model.addAttribute("totalEur", currencyService.getEurValueFromUsd(sumPrice.get("intPartPrice"), sumPrice.get("fractPartPrice")));
        model.addAttribute("totalByn", currencyService.getBynValueFromUsd(sumPrice.get("intPartPrice"), sumPrice.get("fractPartPrice")));
        model.addAttribute("defaultBankCard", bankCardService.findActiveByUserId(user.getId().toString()));
        model.addAttribute("bankCards", bankCardService.findNonActiveByUserId(user.getId().toString()));
        return "order/orders";
    }

    @GetMapping("/orders/create")
    public String createOrder (@RequestParam(name = "advertId") String advertId,
                             @AuthenticationPrincipal User user) {
        orderService.createOrder(user, advertService.findById(advertId));
        return "redirect:/adverts/" + advertId;
    }

    @GetMapping("/orders/{order}/delete")
    @ResponseBody
    public void createOrder (@PathVariable Order order, @AuthenticationPrincipal User user) {
        orderService.deleteOrder(order, user);
    }

    @GetMapping("/orders/{order}/confirm")
    @ResponseBody
    public Boolean confirmOrder (@PathVariable Order order, @AuthenticationPrincipal User user,
                                @RequestParam(name = "cardId") String cardId,
                                Model model) {
        return orderService.payOrder(order, user, cardId);
        //model.addAttribute("orders", orderService.getOrdersByUserIdNotPaid(user.getId().toString()));
        //model.addAttribute("paidOrders", orderService.getOrdersByUserIdPaid(user.getId().toString()));
        //return "redirect:/orders/my";
    }
}
