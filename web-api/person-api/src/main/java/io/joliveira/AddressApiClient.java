package io.joliveira;

import io.joliveira.address.v1.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AddressApiClient {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Environment environment;

    public Address findAddress(String addressId) {
        try {
            return restTemplate
                    .getForEntity(
                            "http://%s:8081/address/%s".formatted(environment.getProperty("address-api.host", "localhost"), addressId), Address.class)
                    .getBody();
        } catch (Exception e) {
            throw new RuntimeException("Error when trying to find address", e);
        }
    }

}
