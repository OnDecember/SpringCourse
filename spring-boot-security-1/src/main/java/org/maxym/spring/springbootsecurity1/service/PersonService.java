package org.maxym.spring.springbootsecurity1.service;

import org.maxym.spring.springbootsecurity1.model.Person;
import org.maxym.spring.springbootsecurity1.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Optional<Person> findByUsername(String username) {
        return personRepository.findByUsername(username);
    }

    @Transactional
    public void register(Person person) {
        personRepository.save(person);
    }
}
