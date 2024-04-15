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

import br.com.objrodrigues.data.vo.v1.PersonVO;
import br.com.objrodrigues.services.PersonService;


@RestController
@RequestMapping("/api/person/v1")
public class PersonController {
    
    @Autowired
    private PersonService service;

    @GetMapping(
        value ="/get/{id}",
        produces = "application/json"
    )
    public PersonVO findById (
        @PathVariable Long id
    ) throws Exception {
        return service.findById(id);
    }

    @GetMapping(
        value ="/get/all",
        produces = "application/json"
    )
    public List<PersonVO> findAll () throws Exception {
        return service.findAll();
    }

    @PostMapping(
        value = "/create",
        consumes = "application/json",
        produces = "application/json"
    )
    public PersonVO createPerson(@RequestBody PersonVO person) throws Exception {
        return service.createPerson(person);
    }

    @PutMapping(
        value = "/update",
        consumes = "application/json",
        produces = "application/json"
    )
    public PersonVO upatePerson(@RequestBody PersonVO person) throws Exception {
        return service.createPerson(person);
    }

    @DeleteMapping(
        value = "/delete/{id}"
    )
    public void deletePerson(@PathVariable Long id) throws Exception {
        service.deletePerson(id);
    }
}