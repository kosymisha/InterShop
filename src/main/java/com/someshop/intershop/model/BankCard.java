package com.someshop.intershop.model;

import javax.persistence.*;

@Entity
@Table(name = "bank_card")
public class BankCard {

    @Id
    @Column(name = "number_card")
    private String numberCard;

    @Column(name = "first_name_card")
    private String firstNameCard;

    @Column(name = "last_name_card")
    private String lastNameCard;

    @Column(name = "month")
    private String month;

    @Column(name = "year")
    private String year;

    @OneToOne(mappedBy = "card", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private User user;

    public BankCard(String numberCard, String firstNameCard, String lastNameCard, String month, String year) {
        this.numberCard = numberCard;
        this.firstNameCard = firstNameCard;
        this.lastNameCard = lastNameCard;
        this.month = month;
        this.year = year;
    }

    public BankCard() {
    }

    public String getNumberCard() {
        return numberCard;
    }

    public void setNumberCard(String numberCard) {
        this.numberCard = numberCard;
    }

    public String getFirstNameCard() {
        return firstNameCard;
    }

    public void setFirstNameCard(String firstNameCard) {
        this.firstNameCard = firstNameCard;
    }

    public String getLastNameCard() {
        return lastNameCard;
    }

    public void setLastNameCard(String lastNameCard) {
        this.lastNameCard = lastNameCard;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
