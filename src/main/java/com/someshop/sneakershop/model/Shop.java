package com.someshop.sneakershop.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "shop")
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_shop")
    private String nameShop;

    @Column
    private String url;

    @Column(name = "photo_url")
    private String photoURL;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User owner;

    @OneToMany(mappedBy = "shop", cascade = CascadeType.ALL)
    private Set<Announcement> announcements;

    @OneToMany(mappedBy = "shop", cascade = CascadeType.ALL)
    private Set<Comment> comments;

    public Shop(String nameShop, String url, User owner, String photoURL, String description) {
        this.nameShop = nameShop;
        this.url = url;
        this.owner = owner;
        this.photoURL = photoURL;
        this.description = description;
    }

    public Shop() {
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public Set<Announcement> getAnnouncements() {
        return announcements;
    }

    public void setAnnouncements(Set<Announcement> announcements) {
        this.announcements = announcements;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameShop() {
        return nameShop;
    }

    public void setNameShop(String nameShop) {
        this.nameShop = nameShop;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

}
