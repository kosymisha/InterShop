package com.someshop.intershop.controller;

import com.someshop.intershop.dto.CurrencyDto;
import com.someshop.intershop.model.Order;
import com.someshop.intershop.model.User;
import com.someshop.intershop.service.AdvertService;
import com.someshop.intershop.service.CurrencyService;
import com.someshop.intershop.service.OrderService;
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

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private AdvertService advertService;

    @Autowired
    private CurrencyService currencyService;

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
        BigDecimal sumPrice = orderService.sumPrice(orderService.getOrdersByUserIdPaid(user.getId().toString()));
        model.addAttribute("orders", orderService.getOrdersByUserIdNotPaid(user.getId().toString()));
        model.addAttribute("paidOrders", orderService.getOrdersByUserIdPaid(user.getId().toString()));
        model.addAttribute("itemCount", orderService.getOrdersByUserIdPaid(user.getId().toString()).size());
        model.addAttribute("totalUsd", new CurrencyDto(sumPrice, "USD"));
        model.addAttribute("totalEur", currencyService.getEurValueFromUsd(sumPrice));
        model.addAttribute("totalByn", currencyService.getBynValueFromUsd(sumPrice));
        return "order/orders";
    }

    @GetMapping("/orders/create")
    @ResponseBody
    public void createOrder (@RequestParam(name = "advertId") String advertId,
                             @AuthenticationPrincipal User user) {
        orderService.createOrder(user, advertService.findById(advertId));
    }

    @GetMapping("/orders/{order}/delete")
    @ResponseBody
    public void createOrder (@PathVariable Order order, @AuthenticationPrincipal User user) {
        orderService.deleteOrder(order, user);
    }

    @GetMapping("/orders/{order}/confirm")
    public String confirmOrder (@PathVariable Order order, @AuthenticationPrincipal User user,
                                Model model) {
        orderService.payOrder(order, user);
        model.addAttribute("orders", orderService.getOrdersByUserIdNotPaid(user.getId().toString()));
        model.addAttribute("paidOrders", orderService.getOrdersByUserIdPaid(user.getId().toString()));
        return "redirect:/orders/my";
    }
}
