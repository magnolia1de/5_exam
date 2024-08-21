package edu.technischools.java.model;

import java.math.BigDecimal;

public class BasicAccount extends Account {
    public BasicAccount(String accountNumber, Currency currency, String name) {
        super(accountNumber, currency, name, new BigDecimal("0.1"));
    }
}
