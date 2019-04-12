package com.someshop.intershop.controller;

import com.someshop.intershop.model.User;
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
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Controller
public class ProfileController {

    @Autowired
    private UserService userService;

    @GetMapping("/profiles")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String profiles(Model model) {
        model.addAttribute("users", userService.findAll());
        return "profile/profiles";
    }

    @GetMapping("/profiles/{profileUser}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String profilesUser(@PathVariable User profileUser, Model model) {
        model.addAttribute("profileUser", profileUser);
        return "profile/profile";
    }

    @GetMapping("/profiles/my")
    public String profileMy (Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("profileUser", user);
        return "profile/profile";
    }

    @GetMapping("/profiles/{profile}/delete")
    public String deleteProfile (@PathVariable User profile, @AuthenticationPrincipal User user) {
        userService.delete(user, profile);
        return "redirect:/logout";
    }

    @GetMapping("/profiles/{profile}/active")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String setActiveProfile (@PathVariable User profile, @RequestParam(name = "value") Boolean value) {
        userService.setActive(profile, value);
        return "redirect:/profiles/" + profile.getId();
    }

    @PostMapping("/profiles/{profileUser}/role")
    public String userSave(@PathVariable User profileUser,
                           @RequestParam(name = "role") String role,
                           Model model) {
        userService.changeRole(profileUser, role);
        model.addAttribute("profileUser", profileUser);
        return "redirect:/profiles/" + profileUser.getId();
    }

    @GetMapping("/profiles/my/options")
    public String options (@AuthenticationPrincipal User profileUser, Model model) {
        model.addAttribute("profileUser", profileUser);
        return "profile/options";
    }

    @PostMapping("/profiles/my/options/save")
    public String optionsSave (@AuthenticationPrincipal User profileUser, Model model,
                               @RequestParam Map<String, String> form, @RequestParam("photo_url") MultipartFile file) {
        User newUser = userService.changeInfo(profileUser, form, file);
        if (newUser != null) {
            model.addAttribute("message", "Success");
            model.addAttribute("profileUser", newUser);
            return "profile/options";
        } else {
            model.addAttribute("message","User with that email already exists");
            model.addAttribute("profileUser", profileUser);
        return "profile/options";
        }
    }
}
