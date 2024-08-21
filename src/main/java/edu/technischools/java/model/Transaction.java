package edu.technischools.java.model;

import java.math.BigDecimal;
import java.util.Date;

public class Transaction {
    private static long transactionCounter = 0;

    private long transactionID;
    private Date date;
    private String fromAccountNumber;
    private String toAccountNumber;
    private BigDecimal amount;

    public Transaction(Date date, String fromAccountNumber, String toAccountNumber, BigDecimal amount) {
        this.transactionID = ++transactionCounter;
        this.date = new Date();
        this.fromAccountNumber = fromAccountNumber;
        this.toAccountNumber = toAccountNumber;
        this.amount = amount;
    }

    public long getTransactionID() {
        return transactionID;
    }

    public Date getDate() {
        return date;
    }

    public String getFromAccountNumber() {
        return fromAccountNumber;
    }

    public String getToAccountNumber() {
        return toAccountNumber;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setFromAccountNumber(String fromAccountNumber) {
        this.fromAccountNumber = fromAccountNumber;
    }

    public void setToAccountNumber(String toAccountNumber) {
        this.toAccountNumber = toAccountNumber;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
