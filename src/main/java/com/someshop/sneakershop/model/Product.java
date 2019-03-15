package com.someshop.sneakershop.model;

import javax.persistence.*;

@Entity
@Table(name = "product")
public class Product {
    /*
    * id
    * title
    * category_name
    * photo_url
    * product_url
    * description
    * */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String title;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "photo_url")
    private String photoURL;

    @Column(name = "product_url")
    private String productURL;

    @Column
    private String description;

    public Product() {}

    public Product(String title, String categoryName, String photoURL, String productURL, String description) {
        this.title = title;
        this.categoryName = categoryName;
        this.photoURL = photoURL;
        this.productURL = productURL;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
}
