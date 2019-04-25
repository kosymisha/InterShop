package com.someshop.intershop.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "category")
public class Category {
    @Id
    private Long id;

    @Column(name = "category_name")
    private String categoryName;

    /*
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private Set<Product> products;
    */

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private Set<Advert> adverts;

    public Category() {
    }

    public Category(Long id, String categoryName) {
        this.id = id;
        this.categoryName = categoryName;
    }

    public String getIdString() {
        return id.toString();
    }

    public Set<Advert> getAdverts() {
        return adverts;
    }

    public void setAdverts(Set<Advert> adverts) {
        this.adverts = adverts;
    }

    /*
    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
    */

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