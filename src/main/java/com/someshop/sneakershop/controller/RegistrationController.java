package com.someshop.sneakershop.controller;

import com.someshop.sneakershop.model.Role;
import com.someshop.sneakershop.model.User;
import com.someshop.sneakershop.repository.UserRepository;
import com.someshop.sneakershop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("message", "Add new user.");
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser (Model model, User user, @RequestParam("role") String role,
                           @RequestParam(name = "photo_url") MultipartFile file) throws IOException {
        model.addAttribute("message", userService.create(user, role, file));
        return "redirect:/login";
    }

}
