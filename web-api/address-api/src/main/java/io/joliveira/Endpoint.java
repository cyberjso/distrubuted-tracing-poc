package io.joliveira;

import io.joliveira.address.v1.Address;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Endpoint {

    @GetMapping("address/{id}")
    public Address findAddressBy(@PathVariable("id") String zipCode) {
        return new Address(zipCode, "Farrapos Avenue", "POA");
    }

}
