package com.mitocode.shop.model.money;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Objects;

public record Money(Currency currency, BigDecimal amount) {
    public Money{
        Objects.requireNonNull(currency, "'currency' must not be null");
        Objects.requireNonNull(currency, "'amount' must not be null");
        if(amount.scale()>currency.getDefaultFractionDigits()){
            throw new IllegalArgumentException("Scale of amount %s is greater than the number of " +
                    "fraction digits used with the currency $s".formatted(amount,currency));
        }
    }
    public static void main(String[] args) {
        Currency usd = Currency.getInstance("USD");
        BigDecimal amount = new BigDecimal("100.00");
        Money money1 = new Money(usd,amount);
    }
}

