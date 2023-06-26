package io.joliveira;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;

@Entity
@Table(name  =  "person")
public class PersonEntity {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;
    @Column(nullable = false, length = 100)
    private String name;
    @Column(nullable = false, length = 300)
    private String address;
    @Column(nullable = false, length = 300)
    private String addressId;

    public PersonEntity() { }

    public PersonEntity(String name, String finalAddress, String addressId) {
        this.name = name;
        this.address = finalAddress;
        this.addressId =  addressId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }
}
