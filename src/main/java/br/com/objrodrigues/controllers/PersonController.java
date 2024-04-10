package br.com.objrodrigues.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.objrodrigues.model.Person;
import br.com.objrodrigues.services.PersonService;


@RestController
@RequestMapping("/person")
public class PersonController {
    
    @Autowired
    private PersonService service;

    @GetMapping(
        value ="/{id}",
        produces = "application/json"
    )
    public Person findById (
        @PathVariable Long id
    ) throws Exception {
        return service.findById(id);
    }

    @GetMapping(
        value ="/all",
        produces = "application/json"
    )
    public List<Person> findAll () throws Exception {
        return service.findAll();
    }

    @PostMapping(
        value = "/create",
        consumes = "application/json",
        produces = "application/json"
    )
    public Person createPerson(@RequestBody Person person) throws Exception {
        return service.createPerson(person);
    }

    @PutMapping(
        value = "/update",
        consumes = "application/json",
        produces = "application/json"
    )
    public Person upatePerson(@RequestBody Person person) throws Exception {
        return service.createPerson(person);
    }

    @DeleteMapping(
        value = "/delete/{id}"
    )
    public void deletePerson(@PathVariable Long id) throws Exception {
        service.deletePerson(id);
    }
}
