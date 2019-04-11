package com.someshop.intershop.controller;

import com.someshop.intershop.dto.CurrencyDto;
import com.someshop.intershop.model.Order;
import com.someshop.intershop.model.Shop;
import com.someshop.intershop.model.User;
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

import java.math.BigDecimal;
import java.util.List;

@Controller
public class SalesController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CurrencyService currencyService;

    @GetMapping("/sales")
    @PreAuthorize("hasAuthority('SELLER')")
    public String sales (@AuthenticationPrincipal User user, Model model) {
        List<Order> orders = orderService.findAllPaidBySeller(user.getId().toString());
        BigDecimal sumPrice = orderService.sumPrice(orders);
        model.addAttribute("sales", orders);
        model.addAttribute("itemCount", orders.size());
        model.addAttribute("totalUsd", new CurrencyDto(sumPrice, "USD"));
        model.addAttribute("totalEur", currencyService.getEurValueFromUsd(sumPrice));
        model.addAttribute("totalByn", currencyService.getBynValueFromUsd(sumPrice));
        return "sales/sales";
    }

    @GetMapping("/sales/{shop}")
    @PreAuthorize("hasAuthority('SELLER')")
    public String salesWithParams (@PathVariable Shop shop, Model model) {
        List<Order> orders = orderService.findAllPaidByShop(shop.getId().toString());
        BigDecimal sumPrice = orderService.sumPrice(orders);
        model.addAttribute("sales", orders);
        model.addAttribute("itemCount", orders.size());
        model.addAttribute("totalUsd", new CurrencyDto(sumPrice, "USD"));
        model.addAttribute("totalEur", currencyService.getEurValueFromUsd(sumPrice));
        model.addAttribute("totalByn", currencyService.getBynValueFromUsd(sumPrice));
        return "sales/sales";
    }
}
