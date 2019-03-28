package com.someshop.intershop.controller;

import com.someshop.intershop.model.User;
import com.someshop.intershop.repository.UserRepository;
import com.someshop.intershop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProfileController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/profiles")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String profiles(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "profile/profiles";
    }

    @GetMapping("/profiles/{profileUser}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String profilesUser(@PathVariable User profileUser, Model model,
                               @AuthenticationPrincipal User user) {
        model.addAttribute("profileUser", profileUser);
        model.addAttribute("user", user);
        return "profile/profile";
    }

    @GetMapping("/profiles/my")
    public String profileMy (Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("profileUser", user);
        model.addAttribute("user", user);
        return "profile/profile";
    }

    @PostMapping("/profiles/{profileUser}/role")
    public String userSave(@PathVariable User profileUser,
                           @AuthenticationPrincipal User user,
                           @RequestParam(name = "role") String role,
                           Model model) {
        userService.changeRole(profileUser, role);
        model.addAttribute("profileUser", profileUser);
        model.addAttribute("user", user);
        return "profile/profile";
    }
}
