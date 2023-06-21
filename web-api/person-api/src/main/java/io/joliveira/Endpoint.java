package io.joliveira;

import io.joliveira.address.v1.Address;
import io.opentracing.Span;
import io.opentracing.Tracer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class Endpoint {
    @Autowired
    private AddressApiClient addressApiClient;
    @Autowired private Tracer tracer;

    @PostMapping("/person")
    public Person save(@RequestBody  Person person) {
        Span serverSpan = tracer.activeSpan();
        Span span = tracer
                .buildSpan("findAddress")
                .asChildOf(serverSpan)
                .start();
        try {
            String id = UUID.randomUUID().toString();
            Address address = addressApiClient.findAddress(person.getAddressId());
            String finalAddress = "%s - %s".formatted( address.getStreet(), address.getCity());

            return new Person(id, person.getName(), finalAddress, person.getAddressId());
        } catch (Exception e ) {
            throw new RuntimeException(e);
        } finally {
            span.finish();
        }
    }


}
