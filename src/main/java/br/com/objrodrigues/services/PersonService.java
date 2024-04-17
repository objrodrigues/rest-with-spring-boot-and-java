package br.com.objrodrigues.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.objrodrigues.controllers.PersonController;
import br.com.objrodrigues.data.vo.v1.PersonVO;
import br.com.objrodrigues.exceptions.ResourceNotFoundException;
import br.com.objrodrigues.mapper.DozerMapper;
import br.com.objrodrigues.mapper.custom.PersonMapper;
import br.com.objrodrigues.model.Person;
import br.com.objrodrigues.repositories.PersonRepository;

@Service
public class PersonService {

    private static Logger log = LoggerFactory.getLogger(PersonService.class);

    @Autowired
    PersonRepository repository;

    @Autowired
    PersonMapper personMapper;

    public List<PersonVO> findAll() throws Exception{

        log.info("Finding All People.");

        var vos = DozerMapper.parseListObjects(repository.findAll(), PersonVO.class);
        vos.stream()
            .forEach(
                p -> {
                    try {
                        p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            );
        return vos;
    }

    public PersonVO findById(Long id) throws Exception {

        log.info("Finding one PersonVO. PersonVO ID: " + id);

        var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found this ID!"));

        var vo = DozerMapper.parseObject(entity, PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());

        return vo;
    }

    public PersonVO createPerson(PersonVO personVo) throws Exception {

        log.info("Creating Person.");

        var entity = DozerMapper.parseObject(personVo, Person.class);
        var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());

        return vo;
    }

    public PersonVO updatePerson(PersonVO personVo) throws Exception {

        log.info("Updating PersonVO.");

        var entity = repository.findById(personVo.getKey())
            .orElseThrow(() -> new ResourceNotFoundException("No records found this ID!"));

        entity.setFirstName(personVo.getFirstName());
        entity.setLastName(personVo.getLastName());
        entity.setAddress(personVo.getAddress());
        entity.setGender(personVo.getGender());

        var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());

        return vo;
    }

    public void deletePerson(Long id) {

        log.info("Deleting PersonVO.");

        var entity = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("No records found this ID!"));

        repository.delete(entity);
    }
}