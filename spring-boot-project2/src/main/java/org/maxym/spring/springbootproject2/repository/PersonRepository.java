package org.maxym.spring.springbootproject2.repository;

import org.maxym.spring.springbootproject2.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

    Optional<Person> findByFullName(String fullName);
}
