package pl.sdacademy.springdemo.services;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.sdacademy.springdemo.domain.Person;
import pl.sdacademy.springdemo.model.dto.PersonDto;
import pl.sdacademy.springdemo.model.mappers.PersonMapper;
import pl.sdacademy.springdemo.repositories.PersonRepository;

@Service
@Transactional
public class PersonService {

  private final PersonMapper personMapper;
  private final PersonRepository personRepository;

  public PersonService(final PersonMapper personMapper, final PersonRepository personRepository) {
    this.personMapper = personMapper;
    this.personRepository = personRepository;
  }

  public PersonDto createPerson(final PersonDto personDto) {
    final Person person = personMapper.personDtoToPerson(personDto);
    final Person savedPerson = personRepository.save(person);
    return personMapper.personToPersonDto(person);
  }

}
