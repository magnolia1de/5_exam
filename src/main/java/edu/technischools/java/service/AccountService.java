package edu.technischools.java.service;

import edu.technischools.java.model.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    private List<Account> accounts = new ArrayList<>();

    public void createAccount(Account account) {
        accounts.add(account);
    }

    public Account getAccount(String accountNumber) {
        return accounts.stream()
                .filter(account -> account.getAccountNumber().equals(accountNumber))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Account with number " + accountNumber + " does not exist."));
    }

    public Account createAccount(String accountNumber, Currency currency, String name, String accountType) {
        Account account;
        switch (accountType) {
            case "Free":
                account = new FreeAccount(accountNumber, currency, name);
                break;
            case "Premium":
                account = new PremiumAccount(accountNumber, currency, name);
                break;
            default:
                account = new BasicAccount(accountNumber, currency, name);
        }

        account.setAccountNumber(accountNumber);
        account.setCurrency(currency);
        account.setName(name);

        return account;
    }


    public void executeTransaction(Transaction transaction) {
        Account fromAccount = getAccount(transaction.getFromAccountNumber());
        Account toAccount = getAccount(transaction.getToAccountNumber());

        validateTransaction(transaction, fromAccount, toAccount);

        BigDecimal transactionAmount = transaction.getAmount();
        BigDecimal totalDebit = transactionAmount.add(fromAccount.getFee());

        fromAccount.setBalance(fromAccount.getBalance().subtract(totalDebit));
        toAccount.setBalance(toAccount.getBalance().add(transactionAmount));

        fromAccount.addTransaction(transaction);
        toAccount.addTransaction(transaction);
    }

    private void validateTransaction(Transaction transaction, Account fromAccount, Account toAccount) {
        if (transaction.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Transaction amount must be positive.");
        }

        if (fromAccount.getBalance().compareTo(transaction.getAmount().add(fromAccount.getFee())) < 0) {
            throw new IllegalStateException("Insufficient funds to complete the transaction.");
        }

        if (toAccount == null) {
            throw new IllegalArgumentException("Destination account does not exist.");
        }
    }

    public void performTransaction(Transaction transaction) {

    }
}
