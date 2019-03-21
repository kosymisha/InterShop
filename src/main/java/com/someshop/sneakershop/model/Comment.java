package com.someshop.sneakershop.model;

import com.someshop.sneakershop.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.validation.constraints.Null;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "date")
    private Date date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "shop_id")
    private Shop shop;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "announcement_id")
    private Announcement announcement;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    @Column(name = "message")
    private String message;

    public Comment() {
    }

    public Comment(Announcement announcement, User author, String message) {
        this.shop = null;
        this.announcement = announcement;
        this.author = author;
        this.message = message;
        this.date = new Date();
    }

    public Comment(Shop shop, User author, String message) {
        this.shop = shop;
        this.announcement = null;
        this.author = author;
        this.message = message;
        this.date = new Date();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Shop getShop() {
        return shop;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public Announcement getAnnouncement() {
        return announcement;
    }

    public void setAnnouncement(Announcement announcement) {
        this.announcement = announcement;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStringDate() {
        return new SimpleDateFormat("yyyy.MM.dd HH:mm").format(this.date);
    }
}
