package com.someshop.intershop.model;

import javax.persistence.*;

@Entity
@Table(name = "category")
public class Category {
    @Id
    private Long id;

    @Column(name = "category_name")
    private String categoryName;

    public Category() {
    }

    public Category(Long id, String categoryName) {
        this.id = id;
        this.categoryName = categoryName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
//  https://pages.ebay.com/sellerinformation/growing/categorychanges/clothing-all.html