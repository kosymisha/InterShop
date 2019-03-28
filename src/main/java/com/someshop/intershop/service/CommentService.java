package com.someshop.intershop.service;

import com.someshop.intershop.model.Announcement;
import com.someshop.intershop.model.Comment;
import com.someshop.intershop.model.Shop;
import com.someshop.intershop.model.User;
import com.someshop.intershop.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
