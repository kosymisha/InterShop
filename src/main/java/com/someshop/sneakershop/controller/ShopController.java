package com.someshop.sneakershop.controller;

import com.someshop.sneakershop.model.Comment;
import com.someshop.sneakershop.model.Role;
import com.someshop.sneakershop.model.Shop;
import com.someshop.sneakershop.model.User;
import com.someshop.sneakershop.repository.ShopRepository;
import com.someshop.sneakershop.service.CommentService;
import com.someshop.sneakershop.service.ShopService;
import com.someshop.sneakershop.service.UserService;
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

import java.io.IOException;
import java.util.Map;

@Controller
public class ShopController {

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private ShopService shopService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/shops")
    public String shops(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("shopList", shopRepository.findAllOrderByOwner(user));
        model.addAttribute("user", user);
        return "shop/shops";
    }

    @GetMapping("/shops/{shop}")
    public String shopsById (@PathVariable Shop shop, Model model,
                             @AuthenticationPrincipal User user) {
        model.addAttribute("shop", shop);
        model.addAttribute("comments", commentService.findAllByShop(shop));
        model.addAttribute("user", user);
        return "shop/shop";
    }

    @GetMapping("/shops/{shop}/delete")
    public String deleteShop (@PathVariable Shop shop, @AuthenticationPrincipal User user, Model model) {
        shopService.delete(shop, user);
        model.addAttribute("shopList", shopRepository.findAll());
        model.addAttribute("user", user);
        return "shop/shops";
    }

    @GetMapping("shops/{shop}/comments/{comment}/delete")
    public String deleteComment (@PathVariable Shop shop, @PathVariable Comment comment,
                                 @AuthenticationPrincipal User user, Model model) {
        commentService.delete(comment);
        model.addAttribute("shop", shop);
        model.addAttribute("comments", commentService.findAllByShop(shop));
        model.addAttribute("user", user);
        return "shop/shop";
    }


    @GetMapping("shops/create")
    public String createShop () {
        return "shop/create";
    }

    @PostMapping("/shops/{shop}/comments/create")
    public String commentCreate (@PathVariable Shop shop, Model model, @AuthenticationPrincipal User user,
                                 @RequestParam("commentBox") String message) {
        commentService.createInShop(user, message, shop);
        model.addAttribute("shop", shop);
        model.addAttribute("comments", commentService.findAllByShop(shop));
        model.addAttribute("user", user);
        return "shop/shop";
    }

    @PostMapping("shops/create")
    @PreAuthorize("hasAuthority('SELLER')")
    public String createShop (@AuthenticationPrincipal User user,
                              @RequestParam Map<String, String> form,
                              @RequestParam("photo_url") MultipartFile file) throws IOException {
        shopService.create(form, file, user);
        return "shop/create";
    }
}
