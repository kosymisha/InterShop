package com.someshop.intershop.dto;

import com.someshop.intershop.model.Shop;

import java.math.BigDecimal;

public class AdvertDto {
    private Long id;
    private String currency;
    private Integer intPartPrice;
    private Integer fractPartPrice;
    private Integer views;
    private String title;
    private String photoURL;
    private String categoryName;
    private Shop shop;

    public AdvertDto() {
    }

    public AdvertDto(Long id, String currency, Integer views, String title, String photoURL,
                     String categoryName, Shop shop, Integer intPartPrice, Integer fractPartPrice) {
        this.id = id;
        this.currency = currency;
        this.intPartPrice = intPartPrice;
        this.fractPartPrice = fractPartPrice;
        this.views = views;
        this.title = title;
        this.photoURL = photoURL;
        this.categoryName = categoryName;
        this.shop = shop;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Integer getIntPartPrice() {
        return intPartPrice;
    }

    public void setIntPartPrice(Integer intPartPrice) {
        this.intPartPrice = intPartPrice;
    }

    public Integer getFractPartPrice() {
        return fractPartPrice;
    }

    public void setFractPartPrice(Integer fractPartPrice) {
        this.fractPartPrice = fractPartPrice;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }
}
