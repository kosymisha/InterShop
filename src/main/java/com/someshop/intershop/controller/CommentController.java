package com.someshop.intershop.controller;

import com.someshop.intershop.model.Advert;
import com.someshop.intershop.model.Comment;
import com.someshop.intershop.model.Shop;
import com.someshop.intershop.model.User;
import com.someshop.intershop.service.ShopService;
import com.someshop.intershop.service.impl.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.LinkedList;
import java.util.Set;

@Controller
public class CommentController {

    @Autowired
    private CommentServiceImpl commentService;

    @GetMapping("shops/{shop}/comments/{comment}/delete")
    public String deleteShopComment (@PathVariable Shop shop, @PathVariable Comment comment,
                                     @AuthenticationPrincipal User user, Model model) {
        commentService.delete(comment, user);
        model.addAttribute("shopId", shop.getId());
        model.addAttribute("comments", shop.getCommentsOrderByDate());
        return "/parts/shopComments";
    }

    @PostMapping("/shops/{shop}/comments/create")
    public String createShopComment (@PathVariable Shop shop, Model model, @AuthenticationPrincipal User user,
                                     @RequestParam("commentBox") String message) {
        commentService.createInShop(user, message, shop);
        model.addAttribute("shopId", shop.getId());
        model.addAttribute("comments", shop.getCommentsOrderByDate());
        return "/parts/shopComments";
    }

    @GetMapping("adverts/{advert}/comments/{comment}/delete")
    public String deleteAdvertComment (@PathVariable Advert advert, @PathVariable Comment comment,
                                             @AuthenticationPrincipal User user, Model model) {
        commentService.delete(comment, user);
        model.addAttribute("advertId", advert.getId());
        model.addAttribute("comments", advert.getCommentsOrderByDate());
        return "/parts/advertComments";
    }

    @PostMapping("/adverts/{advert}/comments/create")
    public String createAdvertComment (@PathVariable Advert advert, Model model, @AuthenticationPrincipal User user,
                                       @RequestParam("commentBox") String message) {
        commentService.createInAdvert(user, message, advert);
        model.addAttribute("advertId", advert.getId());
        model.addAttribute("comments", advert.getCommentsOrderByDate());
        return "/parts/advertComments";
    }

}
