package pl.sdacademy.springdemo.services;


import org.springframework.stereotype.Service;

import pl.sdacademy.springdemo.model.mappers.PersonMapper;

@Service
public class PersonService {

  private final PersonMapper personMapper;

  public PersonService(final PersonMapper personMapper) {
    this.personMapper = personMapper;
  }
}
