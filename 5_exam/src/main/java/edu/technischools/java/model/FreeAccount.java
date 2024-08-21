package edu.technischools.java.model;

import java.math.BigDecimal;

public class FreeAccount extends Account {
    public FreeAccount(String accountNumber, Currency currency, String name) {
        super(accountNumber, currency, name, BigDecimal.ZERO);
    }
}
