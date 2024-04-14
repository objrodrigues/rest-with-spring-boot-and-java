package br.com.objrodrigues.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.objrodrigues.exceptions.ResourceNotFoundException;
import br.com.objrodrigues.model.Person;
import br.com.objrodrigues.repositories.PersonRepository;

@Service
public class PersonService {

    private static Logger log = LoggerFactory.getLogger(PersonService.class);

    @Autowired
    PersonRepository repository;

    public Person findById(Long id) {

        log.info(
            "Finding one Person. Person ID: " + id
        );

        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found this ID!"));
    }

    public List<Person> findAll() {

        log.info(
            "Finding All People."
        );

        return repository.findAll();
    }

    public Person createPerson(Person person) {

        log.info(
            "Creating Person."
        );

        return repository.save(person);
    }

    public Person updatePerson(Person person) {

        log.info(
            "Updating Person."
        );

        Person entity = repository.findById(person.getId())
            .orElseThrow(() -> new ResourceNotFoundException("No records found this ID!"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAdress(person.getAdress());
        entity.setGender(person.getGender());

        return repository.save(entity);
    }

    public void deletePerson(Long id) {

        log.info(
            "Deleting Person."
        );

        Person entity = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("No records found this ID!"));

        repository.delete(entity);
    }
}