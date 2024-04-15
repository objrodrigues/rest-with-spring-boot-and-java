package br.com.objrodrigues.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.objrodrigues.data.vo.v2.PersonVOV2;
import br.com.objrodrigues.services.PersonService;


@RestController
@RequestMapping("/api/person/v2")
public class PersonControllerV2 {
    
    @Autowired
    private PersonService service;

    @PostMapping(
        value = "/create",
        consumes = "application/json",
        produces = "application/json"
    )
    public PersonVOV2 createPersonV2(@RequestBody PersonVOV2 person) throws Exception {
        return service.createPersonV2(person);
    }

}
