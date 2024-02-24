package org.maxym.spring.controller;

import jakarta.validation.Valid;
import org.maxym.spring.model.Person;
import org.maxym.spring.service.BookService;
import org.maxym.spring.service.PersonService;
import org.maxym.spring.util.PersonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
public class PersonController {

    private final PersonService personService;
    private final BookService bookService;

    private final PersonValidator personValidator;

    @Autowired
    public PersonController(PersonService personService, BookService bookService, PersonValidator personValidator) {
        this.personService = personService;
        this.bookService = bookService;
        this.personValidator = personValidator;
    }

    @GetMapping
    public String peoplePage(Model model) {
        model.addAttribute("people", personService.findAll());
        return "people/people";
    }

    @GetMapping("/{id}")
    public String personPage(@PathVariable("id") int id,
                             Model model) {

        Person person = personService.findById(id);
        if (person.getPerson_id() != 0) {
            model.addAttribute("person", person);
            model.addAttribute("books", bookService.findAllByPerson(person));
            return "/people/person";
        }
        return "redirect:/people/new";
    }

    @PatchMapping("/{id}")
    public String updatePerson(@PathVariable("id") int id,
                               @ModelAttribute("person") @Valid Person person,
                               BindingResult bindingResult) {

        person.setPerson_id(id);
        personValidator.validate(person, bindingResult);

        if (bindingResult.hasErrors()) {
            return "people/edit";
        }

        personService.update(person, id);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable("id") int id) {
        personService.delete(id);
        return "redirect:/people";
    }

    @GetMapping("/new")
    public String newPersonPage(@ModelAttribute("person") Person person) {
        return "/people/new";
    }

    @PostMapping
    public String createNewPerson(@ModelAttribute("person") @Valid Person person,
                                  BindingResult bindingResult) {

        personValidator.validate(person, bindingResult);

        if (bindingResult.hasErrors()) {
            return "people/new";
        }

        personService.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String editPersonPage(@PathVariable("id") int id,
                                 Model model) {

        Person person = personService.findById(id);
        if (person.getPerson_id() != 0) {
            model.addAttribute("person", person);
            return "/people/edit";
        }
        return "redirect:/people/new";
    }
}