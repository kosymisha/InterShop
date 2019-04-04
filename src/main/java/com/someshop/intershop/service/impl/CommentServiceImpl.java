package com.someshop.intershop.service.impl;

import com.someshop.intershop.model.Advert;
import com.someshop.intershop.model.Comment;
import com.someshop.intershop.model.Shop;
import com.someshop.intershop.model.User;
import com.someshop.intershop.repository.CommentRepository;
import com.someshop.intershop.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> findAllByAdvert (Advert advert) {
        return commentRepository.findAllByAdvert(advert);
    }

    public List<Comment> findAllByShop (Shop shop) {
        return commentRepository.findAllByShop(shop);
    }

    public void createInAdvert (User user, String message, Advert advert) {
        if (message != "") commentRepository.save(new Comment(advert, user, message));
    }

    public void createInShop (User user, String message,Shop shop) {
        if (message != "") commentRepository.save(new Comment(shop, user, message));
    }

    public void delete (Comment comment) {
        commentRepository.delete(comment);
    }
}
