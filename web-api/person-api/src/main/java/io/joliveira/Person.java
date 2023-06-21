package io.joliveira;

public class Person {
    private String id;
    private String name;
    private String address;
    private String addressId;

    public Person() { }

    public Person(String id, String name, String address, String addressId) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.addressId = addressId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getAddressId() {
        return addressId;
    }
}
