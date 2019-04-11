package com.someshop.intershop.service;

import com.someshop.intershop.model.Advert;
import com.someshop.intershop.model.Comment;
import com.someshop.intershop.model.Shop;
import com.someshop.intershop.model.User;

import java.util.List;
import java.util.Set;


public interface CommentService {
    List<Comment> orderByDate (List<Comment> comments);
    void createInAdvert (User user, String message, Advert advert);
    void createInShop (User user, String message,Shop shop);
    void delete (Comment comment, User user);
}
