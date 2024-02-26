package org.maxym.spring.springrestapiapp1.controller;

import jakarta.validation.Valid;
import org.maxym.spring.springrestapiapp1.dto.PersonDTO;
import org.maxym.spring.springrestapiapp1.model.Person;
import org.maxym.spring.springrestapiapp1.service.PersonService;
import org.maxym.spring.springrestapiapp1.util.PersonErrorResponse;
import org.maxym.spring.springrestapiapp1.util.PersonNotCreatedException;
import org.maxym.spring.springrestapiapp1.util.PersonNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/people")
public class PersonController {

    private final PersonService personService;
    private final ModelMapper modelMapper;

    @Autowired
    public PersonController(PersonService personService, ModelMapper modelMapper) {
        this.personService = personService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<List<PersonDTO>> getPeople() {
        return ResponseEntity.status(HttpStatus.OK).body(
                personService.findAll().stream()
                        .map(this::convertToPersonDTO)
                        .toList()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonDTO> getPerson(@PathVariable("id") int id) {
        return ResponseEntity.status(HttpStatus.OK).body(convertToPersonDTO(personService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid PersonDTO personDTO,
                                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();

            errors.forEach(error -> errorMessage
                    .append(error.getField())
                    .append(" - ")
                    .append(error.getDefaultMessage())
                    .append(";"));

            throw new PersonNotCreatedException(errorMessage.toString());
        }

        personService.save(convertToPerson(personDTO));

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    private Person convertToPerson(PersonDTO personDTO) {
        return modelMapper.map(personDTO, Person.class);
    }

    private PersonDTO convertToPersonDTO(Person person) {
        return modelMapper.map(person, PersonDTO.class);
    }

    @ExceptionHandler
    private ResponseEntity<PersonErrorResponse> handleException(PersonNotFoundException exception) {
        PersonErrorResponse response = new PersonErrorResponse("Person Not Found", System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<PersonErrorResponse> handleException(PersonNotCreatedException exception) {
        PersonErrorResponse response = new PersonErrorResponse(exception.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
