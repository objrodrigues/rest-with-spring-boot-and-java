package br.com.objrodrigues.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.objrodrigues.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{

}
