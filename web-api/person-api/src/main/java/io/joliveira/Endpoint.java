package io.joliveira;

import io.opentracing.Span;
import io.opentracing.Tracer;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Endpoint {
    @Autowired
    private
    AddressApiClient addressApiClient;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private Tracer tracer;

    @PostMapping("/person")
    public Person save(@RequestBody  Person person) {
        Span serverSpan = tracer.activeSpan();
        Span span = tracer
                .buildSpan("addressApiClient")
                .asChildOf(serverSpan)
                .start();
        try {
            String finalAddress = addressApiClient
                    .findAddress(person.getAddressId())

                    .map(address -> "%s - %s".formatted( address.getStreet(), address.getCity()))
                    .orElse(Strings.EMPTY);

            PersonEntity entity = personRepository.save(new PersonEntity(person.getName(), finalAddress, person.getAddressId()));
            return new Person(entity.getId(), person.getName(), finalAddress, person.getAddressId());
        } catch (Exception e ) {
            throw new RuntimeException(e);
        } finally {
            span.finish();
        }
    }


}
