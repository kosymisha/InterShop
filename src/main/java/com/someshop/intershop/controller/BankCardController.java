package com.someshop.intershop.controller;

import com.someshop.intershop.model.BankCard;
import com.someshop.intershop.model.User;
import com.someshop.intershop.service.BankCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class BankCardController {

    @Autowired
    private BankCardService bankCardService;

    @PutMapping("/profiles/my/bankCards/{bankCard}")
    public String makeDefault (@AuthenticationPrincipal User user, @PathVariable BankCard bankCard,
                               Model model) {
        bankCardService.makeDefault(user, bankCard);
        model.addAttribute("activeCard", bankCardService.findActiveByUserId(user.getId().toString()));
        model.addAttribute("nonActiveCards", bankCardService.findNonActiveByUserId(user.getId().toString()));
        return "parts/bankCards";
    }

    @DeleteMapping("/profiles/my/bankCards/{bankCard}")
    public String delete (@AuthenticationPrincipal User user, @PathVariable BankCard bankCard, Model model) {
        bankCardService.delete(bankCard);
        model.addAttribute("activeCard", bankCardService.findActiveByUserId(user.getId().toString()));
        model.addAttribute("nonActiveCards", bankCardService.findNonActiveByUserId(user.getId().toString()));
        return "parts/bankCards";
    }

    @PostMapping("/profiles/my/bankCards")
    public String create (@AuthenticationPrincipal User user, Model model,
                          @RequestParam Map<String, String> form) {
        bankCardService.create(form, user);
        model.addAttribute("activeCard", bankCardService.findActiveByUserId(user.getId().toString()));
        model.addAttribute("nonActiveCards", bankCardService.findNonActiveByUserId(user.getId().toString()));
        return "parts/bankCards";
    }

}
