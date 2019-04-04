package com.someshop.intershop.controller;

import com.someshop.intershop.model.Advert;
import com.someshop.intershop.model.Comment;
import com.someshop.intershop.model.Shop;
import com.someshop.intershop.model.User;
import com.someshop.intershop.service.impl.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CommentController {

    @Autowired
    private CommentServiceImpl commentService;

    @GetMapping("shops/{shop}/comments")
    public String getShopComments(Model model, @AuthenticationPrincipal User user,
                                  @PathVariable Shop shop) {
        model.addAttribute("user", user);
        model.addAttribute("object", shop);
        model.addAttribute("comments", commentService.findAllByShop(shop));
        return "/parts/shopComments";
    }

    @GetMapping("adverts/{advert}/comments")
    public String getAdvertComments(Model model, @AuthenticationPrincipal User user,
                                  @PathVariable Advert advert) {
        model.addAttribute("user", user);
        model.addAttribute("object", advert);
        model.addAttribute("comments", commentService.findAllByAdvert(advert));
        return "/parts/advertComments";
    }

    @GetMapping("shops/{shop}/comments/{comment}/delete")
    public String deleteShopComment (@PathVariable Shop shop, @PathVariable Comment comment,
                                     @AuthenticationPrincipal User user, Model model) {
        commentService.delete(comment);
        model.addAttribute("object", shop);
        model.addAttribute("comments", commentService.findAllByShop(shop));
        model.addAttribute("user", user);
        return "/parts/shopComments";
    }

    @PostMapping("/shops/{shop}/comments/create")
    public String createShopComment (@PathVariable Shop shop, Model model, @AuthenticationPrincipal User user,
                                     @RequestParam("commentBox") String message) {
        commentService.createInShop(user, message, shop);
        model.addAttribute("object", shop);
        model.addAttribute("comments", commentService.findAllByShop(shop));
        model.addAttribute("user", user);
        return "/parts/shopComments";
    }

    @GetMapping("adverts/{advert}/comments/{comment}/delete")
    public String deleteAdvertComment (@PathVariable Advert advert, @PathVariable Comment comment,
                                             @AuthenticationPrincipal User user, Model model) {
        commentService.delete(comment);
        model.addAttribute("object", advert);
        model.addAttribute("comments", commentService.findAllByAdvert(advert));
        model.addAttribute("user", user);
        return "/parts/advertComments";
    }

    @PostMapping("/adverts/{advert}/comments/create")
    public String createAdvertComment (@PathVariable Advert advert, Model model, @AuthenticationPrincipal User user,
                                       @RequestParam("commentBox") String message) {
        commentService.createInAdvert(user, message, advert);
        model.addAttribute("object", advert);
        model.addAttribute("comments", commentService.findAllByAdvert(advert));
        model.addAttribute("user", user);
        return "/parts/advertComments";
    }

}
