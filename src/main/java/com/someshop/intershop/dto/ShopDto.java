package com.someshop.intershop.dto;


import com.someshop.intershop.model.User;

public class ShopDto {
    private Long id;
    private String nameShop;
    private String photoURL;
    private User owner;


    public ShopDto(Long id, String nameShop, String photoURL, User owner) {
        this.id = id;
        this.nameShop = nameShop;
        this.photoURL = photoURL;
        this.owner = owner;
    }

    public ShopDto(String nameShop) {
        this.nameShop = nameShop;
    }

    public ShopDto() {
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

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
