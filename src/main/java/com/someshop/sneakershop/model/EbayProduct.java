package com.someshop.sneakershop.model;

public class EbayProduct {

    private String title;
    private String categoryName;
    private String photoURL;
    private String productURL;
    private String currency;
    private String price;

    public EbayProduct(String title, String categoryName, String photoURL, String productURL, String currency, String price) {
        this.title = title;
        this.categoryName = categoryName;
        this.photoURL = photoURL;
        this.productURL = productURL;
        this.currency = currency;
        this.price = price;
    }

    public EbayProduct() {}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public String getProductURL() {
        return productURL;
    }

    public void setProductURL(String productURL) {
        this.productURL = productURL;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
