package io.joliveira;

import io.joliveira.address.v1.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;

@RestController
public class Endpoint {

    @Autowired
    private AddressRepository repository;

    @GetMapping("address/{id}")
    public Address findAddressBy(@PathVariable("id") String id) {
        try {
            AddressEntity entity =  repository.getReferenceById(id);
            return new Address(id, entity.getStreet(), entity.getCity());
        } catch (EntityNotFoundException e) {
            throw new AddressNotFoundException("Address %s not found".formatted(id));
        }
    }

}
