package com.someshop.intershop.service;

import com.someshop.intershop.model.Advert;
import com.someshop.intershop.model.Comment;
import com.someshop.intershop.model.Shop;
import com.someshop.intershop.model.User;

import java.util.List;


public interface CommentService {
    List<Comment> findAllByAdvert (Advert advert);
    List<Comment> findAllByShop (Shop shop);
    void createInAdvert (User user, String message, Advert advert);
    void createInShop (User user, String message,Shop shop);
    void delete (Comment comment);
}
