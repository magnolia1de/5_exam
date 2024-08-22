package edu.technischools.java.service.servieceTest;

import edu.technischools.java.model.*;
import edu.technischools.java.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class AccountServiceTest {
    private AccountService accountService;

    @BeforeEach
    public void setUp() {
        accountService = new AccountService();
    }

    @Test
    public void testCreateAccount() {
        BasicAccount account = new BasicAccount("123", Currency.PLN, "Test Account");
        accountService.createAccount(account);

        assertEquals(account, accountService.getAccount("123"));
    }

    @Test
    public void testAccountBalance() {
        BasicAccount account = new BasicAccount("123", Currency.PLN, "Test Account");
        account.setBalance(new BigDecimal("1000.00"));
        accountService.createAccount(account);

        assertEquals(new BigDecimal("1000.00"), accountService.getAccount("123").getBalance());
    }

    @Test
    public void testTransaction() {
        BasicAccount account = new BasicAccount("123", Currency.PLN, "Test Account");
        accountService.createAccount(account);

        Transaction transaction = new Transaction(new Date(), "Sender", "Receiver", new BigDecimal("100.00"));
        account.addTransaction(transaction);

        assertEquals(1, account.getTransactions().size());
        assertEquals(transaction, account.getTransactions().get(0));
    }

    @Test
    public void testDifferentAccountTypes() {
        PremiumAccount premiumAccount = new PremiumAccount("456", Currency.USD, "Premium Account");
        FreeAccount freeAccount = new FreeAccount("789", Currency.EUR, "Free Account");

        accountService.createAccount(premiumAccount);
        accountService.createAccount(freeAccount);

        assertEquals(new BigDecimal("0.5"), premiumAccount.getFee());
        assertEquals(new BigDecimal("0"), freeAccount.getFee());
    }
}
