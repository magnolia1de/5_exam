package edu.technischools.java.web;

import edu.technischools.java.model.Account;
import edu.technischools.java.model.Client;
import edu.technischools.java.model.Currency;
import edu.technischools.java.model.Transaction;
import edu.technischools.java.service.AccountService;
import edu.technischools.java.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bank")
public class BankController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private AccountService accountService;

    public BankController(AccountService accountService) {
        this.accountService = accountService;
    }

    public void createNewAccount() {
        // Przykładowe dane do tworzenia konta
        String accountNumber = "123456789";
        BigDecimal initialBalance = new BigDecimal("1000.00");
        BigDecimal fee = new BigDecimal("5.00");

        Account newAccount = accountService.createAccount(accountNumber, Currency.EUR ,"przelew" , fee);

        System.out.println("Utworzono nowe konto: " + newAccount.getAccountNumber());
    }

    @PostMapping("/addClient")
    public ResponseEntity<String> addClient(@Valid @RequestBody Client client) {
        clientService.addClient(client);
        return new ResponseEntity<>("Client added successfully", HttpStatus.CREATED);
    }

    @GetMapping("/clients")
    public ResponseEntity<List<Client>> getAllClients() {
        List<Client> clients = clientService.getAllClients();
        if (clients.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @PostMapping("/createAccount")
    public ResponseEntity<String> createAccount(@Valid @RequestBody Account account) {
        accountService.createAccount(account);
        return new ResponseEntity<>("Account created successfully", HttpStatus.CREATED);
    }

    @PostMapping("/performTransaction")
    public ResponseEntity<String> performTransaction(@Valid @RequestBody Transaction transaction) {
        Account fromAccount = accountService.getAccount(transaction.getFromAccountNumber());
        Account toAccount = accountService.getAccount(transaction.getToAccountNumber());

        if (fromAccount == null || toAccount == null) {
            return new ResponseEntity<>("One or both accounts not found", HttpStatus.NOT_FOUND);
        }

        accountService.performTransaction(transaction);
        return new ResponseEntity<>("Transaction performed successfully", HttpStatus.OK);
    }

    @GetMapping("/client/{pesel}")
    public ResponseEntity<Client> getClientByPesel(@PathVariable String pesel) {
        Optional<Client> client = clientService.findClientByPesel(pesel);
        return client.map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
