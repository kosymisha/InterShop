package com.someshop.sneakershop.controller;

import com.someshop.sneakershop.model.User;
import com.someshop.sneakershop.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private UserRepo userRepo;

    @GetMapping
    public String profile(@AuthenticationPrincipal User user,
                          Map<String, Object> model
    ){
        model.put("user", user);
        return "profile";
    }

    @PostMapping
    public String changeInfo (@RequestParam("firstname") String firstName,
                              @RequestParam("lastname") String lastName,
                              @RequestParam("email") String email,
                              Map<String, Object> model,
                              @AuthenticationPrincipal User user) {
        User userFromDb = userRepo.findByEmail(email);
        if (userFromDb != null){
            if(userFromDb.getId() != user.getId()) {
                //model.put("messageEmail", "User exists.");
                model.put("user", user);
                return "profile";
            }
        }
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        userRepo.save(user);
        //model.put("message", "User saved successfully.");
        model.put("user", user);
        return "profile";
    }



}
