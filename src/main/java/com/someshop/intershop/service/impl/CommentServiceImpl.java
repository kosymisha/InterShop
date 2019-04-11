package com.someshop.intershop.service.impl;

import com.someshop.intershop.model.*;
import com.someshop.intershop.repository.CommentRepository;
import com.someshop.intershop.service.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CommentServiceImpl implements CommentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommentServiceImpl.class);

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public List<Comment> orderByDate(List<Comment> comments) {
        Collections.sort(comments, new Comparator<Comment>() {
            public int compare(Comment c1, Comment c2) {
                return c1.getDate().compareTo(c2.getDate()) * -1;
            }
        });
        return comments;
    }

    public void createInAdvert (User user, String message, Advert advert) {
        if (message != "") {
            commentRepository.save(new Comment(advert, user, message));
            LOGGER.info("Comment was created - {}", message);
        }
    }

    public void createInShop (User user, String message,Shop shop) {
        if (message != "") {
            commentRepository.save(new Comment(shop, user, message));
            LOGGER.info("Comment was created - {}", message);
        }
    }

    public void delete (Comment comment, User user) {
        if (user.getRoles().contains(Role.ADMIN) || user.getId().equals(comment.getAuthor().getId())) {
            commentRepository.delete(comment);
            LOGGER.info("Comment was deleted - {}", comment.getMessage());
        }
    }
}
