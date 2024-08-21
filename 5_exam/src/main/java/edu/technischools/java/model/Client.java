package edu.technischools.java.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Client {

    private String firstName;
    private String lastName;
    private Address address;
    private String pesel;
    private String clientID;
    private List<Account> accounts;

    public Client(String firstName, String lastName, Address address, String pesel, String clientID) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.pesel = pesel;
        this.clientID = clientID;
        this.accounts = new ArrayList<>();
    }

    public Client(String firstName, String lastName, Address address) {
        this(firstName, lastName, address, generatePesel(), generateClientID());
    }

    private static String generatePesel() {
        return "00000000000";
    }

    private static String generateClientID() {
        return UUID.randomUUID().toString();
    }

    public void printInfo() {
        System.out.println("Client Info:");
        System.out.println("Name: " + this.firstName + " " + this.lastName);
        System.out.println("Address: " + this.address);
        System.out.println("PESEL: " + this.pesel);
        System.out.println("Client ID: " + this.clientID);
        System.out.println("Accounts: " + this.accounts);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPesel() {
        return pesel;
    }

    public String getClientID() {
        return clientID;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void addAccount(Account account) {
        this.accounts.add(account);
    }

    @Override
    public String toString() {
        return "Client{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address=" + address +
                ", pesel='" + pesel + '\'' +
                ", clientID='" + clientID + '\'' +
                ", accounts=" + accounts +
                '}';
    }
}
