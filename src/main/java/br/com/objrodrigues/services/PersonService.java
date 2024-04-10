package br.com.objrodrigues.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.com.objrodrigues.model.Person;

@Service
public class PersonService {

    private static Logger log = LoggerFactory.getLogger(PersonService.class);

    public Person findById(Long id) {

        log.info(
            "Finding one Person. Person ID: " + id
        );

        Person person = mockPerson(id);

        return person;
    }

    public List<Person> findAll() {

        log.info(
            "Finding All People."
        );

        List<Person> people = new ArrayList<>();

        for (long i = 0; i < 10; i++) {
            Person person = mockPerson(i);
            people.add(person);
        }

        return people;
    }

    public Person createPerson(Person person) {

        log.info(
            "Creating Person."
        );

        return person;
    }

    public Person updatePerson(Person person) {

        log.info(
            "Updating Person."
        );

        return person;
    }

    public void deletePerson(Long id) {

        log.info(
            "Deleting Person."
        );
    }

    private Person mockPerson(long id) {

        Person mockedPerson = new Person(
            id,
            "First Name Person " + id,
            "Last Name Person " + id,
            "Adress Person " + id,
            "Gender Person " + id
        );
        
        return mockedPerson;
    }
}