package io.joliveira;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
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
        String finalAddress = retrieveAddress(person.getAddressId());
        PersonEntity entity = persist(new PersonEntity(person.getName(), finalAddress, person.getAddressId()));

        return new Person(entity.getId(), person.getName(), finalAddress, person.getAddressId());
    }

    private String retrieveAddress(String id) {
        try {
            return addressApiClient
                    .findAddress(id)
                    .map(address -> "%s - %s".formatted( address.getStreet(), address.getCity()))
                    .orElse(Strings.EMPTY);

        }  catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private PersonEntity persist(PersonEntity person) {
        Span serverSpan = tracer.currentSpan();
        Span span = tracer
                .spanBuilder()
                .name("person-api-save")
                .setParent(serverSpan.context())
                .start();
        try{
            String finalAddress = addressApiClient
                    .findAddress(person.getAddressId())
                    .map(address -> "%s - %s".formatted( address.getStreet(), address.getCity()))
                    .orElse(Strings.EMPTY);

            return personRepository.save(new PersonEntity(person.getName(), finalAddress, person.getAddressId()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            span.end();
        }
    }


}
