package com.someshop.sneakershop.model;

import javax.persistence.*;
import javax.validation.constraints.Null;

@Entity
@Table(name = "comment")
public class Comment {
    /*
    * id
    * announcement_id
    * shop_id
    * user_id
    * message
    * */

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

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

    public Comment(Long id, Shop shop, Announcement announcement, User author, String message) {
        this.id = id;
        this.shop = shop;
        this.announcement = announcement;
        this.author = author;
        this.message = message;
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
}
