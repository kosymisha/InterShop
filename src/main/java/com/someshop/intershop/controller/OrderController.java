package com.someshop.intershop.controller;

import com.someshop.intershop.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderController {

    @GetMapping("/orders")
    public String orders () {
        return "orders";
    }

    @GetMapping("/orders/my")
    public String myOrders (@AuthenticationPrincipal User user) {
        return "orders";
    }
}
