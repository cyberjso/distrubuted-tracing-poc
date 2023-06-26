package io.joliveira;

import io.joliveira.address.v1.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.persistence.Entity;
import java.util.Optional;

@Component
public class AddressApiClient {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Environment environment;

    public Optional<Address> findAddress(String addressId) {
        try {
            String uri = "http://%s:8081/address/%s".formatted(environment.getProperty("address-api.host", "localhost"), addressId);
            ResponseEntity<Address> entity  = restTemplate.getForEntity(uri, Address.class);

            return !entity.getStatusCode().equals(HttpStatus.NOT_FOUND) ? Optional.of(entity.getBody()) : Optional.empty();
        } catch (Exception e) {
            throw new RuntimeException("Error when trying to find address", e);
        }
    }

}
