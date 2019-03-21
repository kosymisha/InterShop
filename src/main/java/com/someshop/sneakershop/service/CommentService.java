package com.someshop.sneakershop.service;

import com.someshop.sneakershop.model.Announcement;
import com.someshop.sneakershop.model.Comment;
import com.someshop.sneakershop.model.Shop;
import com.someshop.sneakershop.model.User;
import com.someshop.sneakershop.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> findAllByAnnouncement (Announcement announcement) {
        return commentRepository.findAllByAnnouncement(announcement);
    }

    public List<Comment> findAllByShop (Shop shop) {
        return commentRepository.findAllByShop(shop);
    }

    public void createInAnnouncement (User user, String message, Announcement announcement) {
        if (message != "") commentRepository.save(new Comment(announcement, user, message));
    }

    public void createInShop (User user, String message,Shop shop) {
        if (message != "") commentRepository.save(new Comment(shop, user, message));
    }

    public void delete (Comment comment) {
        commentRepository.delete(comment);
    }
}
