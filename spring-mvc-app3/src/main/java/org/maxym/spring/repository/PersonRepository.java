package org.maxym.spring.repository;

import org.maxym.spring.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

    Optional<Person> findByEmail(String email);

    List<Person> findAllByName(String name);

    List<Person> findAllByNameOrderByAge(String name);

    List<Person> findAllByEmail(String email);

    List<Person> findAllByNameStartingWith(String startingWith);

    List<Person> findAllByNameOrEmail(String name, String email);
}
