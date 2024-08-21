package edu.technischools.java.service;

import edu.technischools.java.model.Address;
import edu.technischools.java.model.Client;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientService {
    private List<Client> clients = new ArrayList<>();

    public void addClient(Client client) {
        clients.add(client);
        System.out.println(clients);
    }

    public List<Client> getAllClients() {
        return clients;
    }

    public List<Client> findClientsByLastName(String lastName) {
        return clients.stream()
                .filter(client -> client.getLastName().equalsIgnoreCase(lastName))
                .collect(Collectors.toList());
    }

    public Optional<Client> findClientByPesel(String pesel) {
        return clients.stream()
                .filter(client -> client.getPesel().equals(pesel))
                .findFirst();
    }

    public Optional<Client> findClientById(String clientId) {
        return clients.stream()
                .filter(client -> client.getClientID().equals(clientId))
                .findFirst();
    }

    public void updateClient(Client updatedClient) {
        clients = clients.stream()
                .map(client -> client.getClientID().equals(updatedClient.getClientID()) ? updatedClient : client)
                .collect(Collectors.toList());
    }

    public void removeClient(String clientId) {
        clients = clients.stream()
                .filter(client -> !client.getClientID().equals(clientId))
                .collect(Collectors.toList());
    }

    public List<Client> findClientsByAddress(Address address) {
        return clients.stream()
                .filter(client -> client.getAddress().getStreet().equalsIgnoreCase(address.getStreet())
                        && client.getAddress().getCity().equalsIgnoreCase(address.getCity())
                        && client.getAddress().getPostalCode().equalsIgnoreCase(address.getPostalCode()))
                .collect(Collectors.toList());
    }
}
