package org.maxym.spring.controller;

import jakarta.validation.Valid;
import org.maxym.spring.model.Person;
import org.maxym.spring.service.ItemService;
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
    private final PersonValidator personValidator;
    private final ItemService itemService;
    @Autowired
    public PersonController(PersonService personService, PersonValidator personValidator, ItemService itemService) {
        this.personService = personService;
        this.personValidator = personValidator;
        this.itemService = itemService;
    }

    @GetMapping
    public String getPeople(Model model) {
        model.addAttribute("people", personService.findAll());

        itemService.findByItemName("airpods");
        itemService.findByOwner(personService.findById(8));

        personService.test();

        return "people/people";
    }

    @GetMapping("/{id}")
    public String getPerson(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personService.findById(id));
        return "people/person";
    }

    @GetMapping("/new")
    public String getCreateForm(@ModelAttribute("person") Person person) {
        return "people/new";
    }

    @PostMapping
    public String createNewPerson(@ModelAttribute("person") @Valid Person person,
                                  BindingResult bindingResult) {

        personValidator.validate(person, bindingResult);

        if (bindingResult.hasErrors())
            return "people/new";

        personService.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String editPerson(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personService.findById(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String updatePerson(@ModelAttribute("person") @Valid Person person,
                               BindingResult bindingResult,
                               @PathVariable("id") int id) {

        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors())
            return "people/edit";

        personService.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable("id") int id) {
        personService.delete(id);
        return "redirect:/people";
    }
}
