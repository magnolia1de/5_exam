package edu.technischools.java.model;

public class ClientBuilder {
    private String name;
    private String email;
    private Address address;

    public ClientBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public ClientBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public ClientBuilder withAddress(Address address) {
        this.address = address;
        return this;
    }

    public Client build() {
        return new Client(name, email, address);
    }
}
