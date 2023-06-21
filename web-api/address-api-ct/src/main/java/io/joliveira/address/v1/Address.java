package io.joliveira.address.v1;

public class Address {
    private String id;
    private String street;
    private String city;

    public Address() {}

    public Address(String id, String street, String city) {
        this.id = id;
        this.street = street;
        this.city = city;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
