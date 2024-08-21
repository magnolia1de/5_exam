package edu.technischools.java.model;

import java.math.BigDecimal;

public class PremiumAccount extends Account {
    public PremiumAccount(String accountNumber, Currency currency, String name) {
        super(accountNumber, currency, name, new BigDecimal("0.5"));
    }
}
