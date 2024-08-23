package edu.technischools.java.service;

import edu.technischools.java.model.Account;

import java.math.BigDecimal;

public class TransferService {

    public void transfer(Account fromAccount, Account toAccount, double amount) throws InsufficientFundsException {
        if (fromAccount.getBalance().compareTo(BigDecimal.valueOf(amount)) < 0) {
            throw new InsufficientFundsException("Niewystarczające środki na koncie do wykonania przelewu");
        }

        fromAccount.debit(amount);
        toAccount.credit(amount);
    }
}
