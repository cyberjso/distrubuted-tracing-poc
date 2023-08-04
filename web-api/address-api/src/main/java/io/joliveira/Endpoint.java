package io.joliveira;

import io.joliveira.address.v1.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;

@RestController
public class Endpoint {
    @Autowired
    private Tracer tracer;

    @Autowired
    private AddressRepository repository;

    @GetMapping("address/{id}")
    @Transactional(readOnly = true)
    public Address findAddressBy(@PathVariable("id") String id) {
        Span serverSpan = tracer.currentSpan();
        Span span = tracer
                .spanBuilder()
                .name("address-api-retrieve")
                .setParent(serverSpan.context())
                .start();
        try {
            delay(id);

            AddressEntity entity =  repository.getReferenceById(id);
            return new Address(id, entity.getStreet(), entity.getCity());
        } catch (EntityNotFoundException e) {
            throw new AddressNotFoundException("Address %s not found".formatted(id));
        } finally {
            span.end();
        }
    }

    /**
     * Explicitly adding a delay on requests with even Ids, so we can see them on the traces
     */
    private void delay(String id) {
        try {
            if (Integer.valueOf(id) % 2 == 0) {
                Thread.sleep(5000);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error while trying to making the process to wait");
        }
    }

}
