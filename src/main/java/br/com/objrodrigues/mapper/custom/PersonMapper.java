package br.com.objrodrigues.mapper.custom;

import java.util.Date;

import org.springframework.stereotype.Service;

import br.com.objrodrigues.data.vo.v2.PersonVOV2;
import br.com.objrodrigues.model.Person;

@Service
public class PersonMapper {

    public static PersonVOV2 convertEnttityTOVo(Person person){
        PersonVOV2 voV2 = new PersonVOV2();

        voV2.setId(person.getId());
        voV2.setAddress(person.getAddress());
        voV2.setBirthDay(new Date());
        voV2.setFirstName(person.getFirstName());
        voV2.setLastName(person.getLastName());
        voV2.setGender(person.getGender());

        return voV2;
    }

    public static Person convertVoTOEntity(PersonVOV2 person){
        Person entity = new Person();

        entity.setId(person.getId());
        entity.setAddress(person.getAddress());
        //entity.setBirthDay(new Date());
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setGender(person.getGender());

        return entity;
    }
}
