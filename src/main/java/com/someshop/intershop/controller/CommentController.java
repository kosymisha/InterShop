package com.someshop.intershop.controller;

import com.someshop.intershop.model.Announcement;
import com.someshop.intershop.model.Comment;
import com.someshop.intershop.model.Shop;
import com.someshop.intershop.model.User;
import com.someshop.intershop.service.CommentService;
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
    private CommentService commentService;

    @GetMapping("shops/{shop}/comments")
    public String getShopComments(Model model, @AuthenticationPrincipal User user,
                                  @PathVariable Shop shop) {
        model.addAttribute("user", user);
        model.addAttribute("object", shop);
        model.addAttribute("comments", commentService.findAllByShop(shop));
        return "comments";
    }

    @GetMapping("announcements/{announcement}/comments")
    public String getAnnouncementComments(Model model, @AuthenticationPrincipal User user,
                                  @PathVariable Announcement announcement) {
        model.addAttribute("user", user);
        model.addAttribute("object", announcement);
        model.addAttribute("comments", commentService.findAllByAnnouncement(announcement));
        return "comments";
    }

    @GetMapping("shops/{shop}/comments/{comment}/delete")
    public String deleteShopComment (@PathVariable Shop shop, @PathVariable Comment comment,
                                     @AuthenticationPrincipal User user, Model model) {
        commentService.delete(comment);
        model.addAttribute("object", shop);
        model.addAttribute("comments", commentService.findAllByShop(shop));
        model.addAttribute("user", user);
        return "comments";
    }

    @PostMapping("/shops/{shop}/comments/create")
    public String createShopComment (@PathVariable Shop shop, Model model, @AuthenticationPrincipal User user,
                                     @RequestParam("commentBox") String message) {
        commentService.createInShop(user, message, shop);
        model.addAttribute("object", shop);
        model.addAttribute("comments", commentService.findAllByShop(shop));
        model.addAttribute("user", user);
        return "comments";
    }

    @GetMapping("announcements/{announcement}/comments/{comment}/delete")
    public String deleteAnnouncementComment (@PathVariable Announcement announcement, @PathVariable Comment comment,
                                     @AuthenticationPrincipal User user, Model model) {
        commentService.delete(comment);
        model.addAttribute("object", announcement);
        model.addAttribute("comments", commentService.findAllByAnnouncement(announcement));
        model.addAttribute("user", user);
        return "comments";
    }

    @PostMapping("/announcements/{announcement}/comments/create")
    public String createAnnouncementComment (@PathVariable Announcement announcement, Model model, @AuthenticationPrincipal User user,
                                     @RequestParam("commentBox") String message) {
        commentService.createInAnnouncement(user, message, announcement);
        model.addAttribute("object", announcement);
        model.addAttribute("comments", commentService.findAllByAnnouncement(announcement));
        model.addAttribute("user", user);
        return "comments";
    }

}
