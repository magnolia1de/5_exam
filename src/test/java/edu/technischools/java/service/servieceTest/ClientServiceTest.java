package edu.technischools.java.service.servieceTest;

import edu.technischools.java.model.*;
import edu.technischools.java.service.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class ClientServiceTest {
    private ClientService clientService;

    @BeforeEach
    public void setUp() {
        clientService = new ClientService();
        clientService.addClient(new Client("John", "Doe", new Address("Street", "City", "00000"), "1234567890", "1"));
        clientService.addClient(new Client("Jane", "Doe", new Address("Avenue", "City", "11111"), "0987654321", "2"));
    }

    @Test
    public void testFindClientsByLastName() {
        assertEquals(2, clientService.findClientsByLastName("Doe").size());
        assertEquals(0, clientService.findClientsByLastName("Smith").size());
    }

    @Test
    public void testFindClientByPesel() {
        assertTrue(clientService.findClientByPesel("1234567890").isPresent());
        assertFalse(clientService.findClientByPesel("0000000000").isPresent());
    }

    @Test
    public void testFindClientById() {
        assertTrue(clientService.findClientById("1").isPresent());
        assertFalse(clientService.findClientById("3").isPresent());
    }

    @Test
    public void testUpdateClient() {
        Client updatedClient = new Client("John", "Smith", new Address("Street", "City", "00000"), "1234567890", "1");
        clientService.updateClient(updatedClient);
        assertEquals("Smith", clientService.findClientById("1").get().getLastName());
    }

    @Test
    public void testRemoveClient() {
        clientService.removeClient("1");
        assertFalse(clientService.findClientById("1").isPresent());
        assertEquals(1, clientService.getAllClients().size());
    }

    @Test
    public void testAddClientWithAccounts() {
        Client client = new Client("Alice", "Smith", new Address("Boulevard", "City", "22222"), "1122334455", "3");
        BasicAccount account = new BasicAccount("123", Currency.PLN, "Savings Account");
        client.addAccount(account);
        clientService.addClient(client);

        assertEquals(1, clientService.findClientById("3").get().getAccounts().size());
    }

    @Test
    public void testFindClientsByAddress() {
        assertEquals(1, clientService.findClientsByAddress(new Address("Street", "City", "00000")).size());
        assertEquals(0, clientService.findClientsByAddress(new Address("Nonexistent", "City", "99999")).size());
    }

    @Test
    public void testUpdateClientAddress() {
        Client client = clientService.findClientById("1").get();
        client.setAddress(new Address("New Street", "New City", "33333"));
        clientService.updateClient(client);

        assertEquals("New Street", clientService.findClientById("1").get().getAddress().getClass());
    }
}
