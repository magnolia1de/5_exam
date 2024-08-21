package edu.technischools.java.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public abstract class Account {
    private String accountNumber;
    private Currency currency;
    private String name;
    private BigDecimal balance;
    private BigDecimal fee;
    private List<Transaction> transactions;

    public Account(String accountNumber, Currency currency, String name, BigDecimal fee) {
        this.accountNumber = accountNumber;
        this.currency = currency;
        this.name = name;
        this.balance = BigDecimal.ZERO;
        this.fee = fee;
        this.transactions = new ArrayList<>();
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public void addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
    }

    public void credit(double amount) {
        BigDecimal amountToCredit = BigDecimal.valueOf(amount);
        this.balance = this.balance.add(amountToCredit);
    }

    public void debit(double amount) {
        BigDecimal amountToDebit = BigDecimal.valueOf(amount).add(this.fee);
        if (this.balance.compareTo(amountToDebit) < 0) {
            throw new IllegalArgumentException("Insufficient balance for the debit operation");
        }
        this.balance = this.balance.subtract(amountToDebit);
    }
}
