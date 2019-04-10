package com.someshop.intershop.service;

import com.someshop.intershop.dto.CurrencyDto;

import java.math.BigDecimal;

public interface CurrencyService {
    BigDecimal convertUsdToEur (BigDecimal price);
    BigDecimal convertUsdToByn (BigDecimal price);
    CurrencyDto getEurValueFromUsd (BigDecimal price);
    CurrencyDto getBynValueFromUsd (BigDecimal price);
}
