package com.someshop.intershop.repository;

import com.someshop.intershop.model.BankCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankCardRepository extends JpaRepository<BankCard, String> {

}
