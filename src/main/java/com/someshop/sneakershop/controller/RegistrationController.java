package com.someshop.sneakershop.controller;

import com.someshop.sneakershop.model.Role;
import com.someshop.sneakershop.model.User;
import com.someshop.sneakershop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;

@Controller
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("message", "Add new user.");
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser (Model model, User user, @RequestParam("role") String role) {
        User userFromDb = userRepository.findByEmail(user.getEmail());
        if (userFromDb != null) {
            model.addAttribute("message", "User exists.");
            return "registration";
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.valueOf(role)));
        userRepository.save(user);
        return "redirect:/login";
    }

}
