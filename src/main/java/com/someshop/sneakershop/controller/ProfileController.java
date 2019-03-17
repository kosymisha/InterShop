package com.someshop.sneakershop.controller;

import com.someshop.sneakershop.model.Role;
import com.someshop.sneakershop.model.User;
import com.someshop.sneakershop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class ProfileController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/profiles")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String profiles(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "profiles";
    }

    @GetMapping("/profiles/{user}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String profilesUser(@PathVariable User user, Model model) {
        model.addAttribute("user", user);
        return "profilesCurrent";
    }

    @GetMapping("/profiles/{user}/options")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String profilesUserOptions(@PathVariable User user,
                               Map<String, Object> model) {
        model.put("user", user);
        model.put("roles", Role.values());
        return "profilesEdit";
    }

    @GetMapping("/profiles/current")
    public String profilesCurrent(@AuthenticationPrincipal User user,
                          Map<String, Object> model){
        model.put("user", user);
        return "profilesCurrent";
    }

    @GetMapping("/profiles/current/options")
    public String profilesOptions(@AuthenticationPrincipal User user,
                          Map<String, Object> model){
        model.put("user", user);
        return "profilesCurrentOptions";
    }

    @PostMapping("/profiles/current/options")
    public String changeInfo (@RequestParam("firstname") String firstName,
                              @RequestParam("lastname") String lastName,
                              @RequestParam("email") String email,
                              Map<String, Object> model,
                              @AuthenticationPrincipal User user) {
        User userFromDb = userRepository.findByEmail(email);
        if (userFromDb != null){
            if(userFromDb.getId() != user.getId()) {
                //model.put("messageEmail", "User exists.");
                model.put("user", user);
                return "profilesCurrent";
            }
        }
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        userRepository.save(user);
        //model.put("message", "User saved successfully.");
        model.put("user", user);
        return "profilesCurrent";
    }

    @PostMapping("/profiles/{user}/options")
    public String userSave(@PathVariable User user,
                           @RequestParam Map<String, String> form,
                           Map<String, Object> model) {
        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());

        user.getRoles().clear();
        /*
        for (String key : form.keySet()) {
            if (roles.contains(key)){
                user.getRoles().add(Role.valueOf(key));
            }
        }*/
        for (String value : form.values()){
            if (roles.contains(value)){
                user.getRoles().add(Role.valueOf(value));
            }
        }

        userRepository.save(user);
        model.put("user", user);
        model.put("roles", Role.values());
        return "/profilesEdit";
    }
}
