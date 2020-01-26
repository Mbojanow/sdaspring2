package pl.sdacademy.springdemo.controllers;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import pl.sdacademy.springdemo.model.dto.PersonDto;
import pl.sdacademy.springdemo.services.PersonService;

@RestController
@RequestMapping("/api/persons")
public class PersonController {

  private final PersonService personService;

  public PersonController(final PersonService personService) {
    this.personService = personService;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public PersonDto createPerson(@Valid @RequestBody final PersonDto personDto) {
    return personService.createPerson(personDto);
  }
}
