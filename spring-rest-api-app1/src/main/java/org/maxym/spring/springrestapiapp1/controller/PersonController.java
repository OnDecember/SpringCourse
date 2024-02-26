package org.maxym.spring.springrestapiapp1.controller;

import org.maxym.spring.springrestapiapp1.model.Person;
import org.maxym.spring.springrestapiapp1.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/people")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public List<Person> getPeople() {
        return personService.findAll();
    }

    @GetMapping("/{id}")
    public Person getPerson(@PathVariable("id") int id) {
        return personService.findById(id);
    }
}
