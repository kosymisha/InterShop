package com.someshop.intershop.dto;

import com.someshop.intershop.model.Product;
import com.someshop.intershop.model.Shop;

import java.math.BigDecimal;

public class AdvertDto {
    private Long id;
    private String currency;
    private BigDecimal price;
    private Integer views;
    private Product product;
    private Shop shop;

    public AdvertDto() {
    }

    public AdvertDto(Long id, String currency, BigDecimal price, Integer views, Product product, Shop shop) {
        this.id = id;
        this.currency = currency;
        this.price = price;
        this.views = views;
        this.product = product;
        this.shop = shop;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }
}
