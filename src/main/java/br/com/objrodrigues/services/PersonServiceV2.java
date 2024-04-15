package br.com.objrodrigues.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.objrodrigues.data.vo.v2.PersonVOV2;
import br.com.objrodrigues.mapper.custom.PersonMapper;
import br.com.objrodrigues.repositories.PersonRepository;

@Service
public class PersonServiceV2 {

    private static Logger log = LoggerFactory.getLogger(PersonService.class);

    @Autowired
    PersonRepository repository;

    @Autowired
    PersonMapper personMapper;

    public PersonVOV2 createPersonV2(PersonVOV2 personVoV2) {

        log.info("Creating Person. V2");

        var entity = PersonMapper.convertVoTOEntity(personVoV2);
        var vo = PersonMapper.convertEnttityTOVo(repository.save(entity));

        return vo;
    }
}