package io.joliveira;

import javax.persistence.*;

@Entity
@Table(name  = "address")
public class AddressEntity {
    @Id
    @GeneratedValue
    private String id;
    @Column(nullable = false, length = 100)
    private String street;
    @Column(nullable = false, length = 100)
    private String city;

    public AddressEntity() { }
    public AddressEntity(String id, String street, String city) {
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
