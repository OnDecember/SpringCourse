package org.maxym.spring.springrestapiapp1.repository;

import org.maxym.spring.springrestapiapp1.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
}
