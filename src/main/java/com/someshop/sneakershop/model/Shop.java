package com.someshop.sneakershop.model;

import javax.persistence.*;

@Entity
@Table(name = "shop")
public class Shop {
    /*
    * id
    * owner (user_id) manyToOne
    * name_shop
    * url
    * */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name_shop")
    private String nameShop;

    @Column
    private String url;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User owner;


    public Shop(String nameShop, String url, User owner) {
        this.nameShop = nameShop;
        this.url = url;
        this.owner = owner;
    }

    public Shop() {
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
