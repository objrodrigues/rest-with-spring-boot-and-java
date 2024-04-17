package br.com.objrodrigues.unittests.mockito.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.objrodrigues.data.vo.v1.PersonVO;
import br.com.objrodrigues.exceptions.RequiredObjectIsNullException;
import br.com.objrodrigues.model.Person;
import br.com.objrodrigues.repositories.PersonRepository;
import br.com.objrodrigues.services.PersonService;
import br.com.objrodrigues.unittests.mapper.mocks.MockPerson;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    MockPerson input;

    @InjectMocks
    private PersonService personService;
    @Mock
    private PersonRepository personRepository;

    @BeforeEach
    void setUpMocks() throws Exception {
        input = new MockPerson();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreatePerson() throws Exception {
        Person person = input.mockEntity(1);
        Person persistedPerson = person;
        persistedPerson.setId(1L);

        PersonVO personVO = input.mockVO(1);
        personVO.setKey(1L);

        when(personRepository.save(person)).thenReturn(persistedPerson);

        var result = personService.createPerson(personVO);
        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());
        assertEquals("Addres Test1", result.getAddress());
        assertEquals("First Name Test1", result.getFirstName());
        assertEquals("Female", result.getGender());
        assertEquals("Last Name Test1", result.getLastName());
        assertTrue(result.toString().contains("links: [</api/person/v1/get/1>;rel=\"self\"]"));
    }

    @Test
    void testCreateWithNullPerson() throws Exception {
        Exception exception = assertThrows(
            RequiredObjectIsNullException.class,
            () -> {personService.createPerson(null);}
        );

        String expectedMessage = "It is not a allowed to persist a nell object!";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testDeletePerson() throws Exception{
        Person person = input.mockEntity(1);
        person.setId(1L);

        when(personRepository.findById(1L)).thenReturn(Optional.of(person));

        personService.deletePerson(1L);
    }

    @Test
    void testFindAll() throws Exception {
        List<Person> personList = input.mockEntityList();

        when(personRepository.findAll()).thenReturn(personList);

        var resultList = personService.findAll();
        assertNotNull(resultList);
        assertEquals(14, personList.size());

        for (int i=0; i<resultList.size(); i++) {
            assertNotNull(resultList.get(i));
            assertNotNull(resultList.get(i).getKey());
            assertNotNull(resultList.get(i).getLinks());
            assertEquals("Addres Test"+i, resultList.get(i).getAddress());
            assertEquals("First Name Test"+i, resultList.get(i).getFirstName());
            assertEquals(((i % 2)==0) ? "Male" : "Female", resultList.get(i).getGender());
            assertEquals("Last Name Test"+i, resultList.get(i).getLastName());
            System.out.println(resultList.get(i).toString());
            assertTrue(resultList.get(i).toString().contains(String.format("links: [</api/person/v1/get/%d>;rel=\"self\"]", i)));
        }
    }

    @Test
    void testFindById() throws Exception {
        Person person = input.mockEntity(1);
        person.setId(1L);

        when(personRepository.findById(1L)).thenReturn(Optional.of(person));

        var result = personService.findById(1L);
        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());
        assertEquals("Addres Test1", result.getAddress());
        assertEquals("First Name Test1", result.getFirstName());
        assertEquals("Female", result.getGender());
        assertEquals("Last Name Test1", result.getLastName());
        assertTrue(result.toString().contains("links: [</api/person/v1/get/1>;rel=\"self\"]"));
    }

    @Test
    void testUpdatePerson() throws Exception {
        Person person = input.mockEntity(1);
        person.setId(1L);
        Person persistedPerson = person;
        persistedPerson.setId(1L);

        PersonVO personVO = input.mockVO(1);
        personVO.setKey(1L);

        when(personRepository.findById(1L)).thenReturn(Optional.of(person));
        when(personRepository.save(person)).thenReturn(persistedPerson);

        var result = personService.updatePerson(personVO);
        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());
        assertEquals("Addres Test1", result.getAddress());
        assertEquals("First Name Test1", result.getFirstName());
        assertEquals("Female", result.getGender());
        assertEquals("Last Name Test1", result.getLastName());
        assertTrue(result.toString().contains("links: [</api/person/v1/get/1>;rel=\"self\"]"));
    }

    @Test
    void testUpdateWithNullPerson() throws Exception {
        Exception exception = assertThrows(
            RequiredObjectIsNullException.class,
            () -> {personService.updatePerson(null);}
        );

        String expectedMessage = "It is not a allowed to persist a nell object!";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}