package pl.sdacademy.springdemo.services;


import java.util.List;
import java.util.stream.Collectors;

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
  private final PersonSearchService personSearchService;

  public PersonService(final PersonMapper personMapper, final PersonRepository personRepository,
                       final PersonSearchService personSearchService) {
    this.personMapper = personMapper;
    this.personRepository = personRepository;
    this.personSearchService = personSearchService;
  }

  public PersonDto createPerson(final PersonDto personDto) {
    final Person person = personMapper.personDtoToPerson(personDto);
    final Person savedPerson = personRepository.save(person);
    return personMapper.personToPersonDto(person);
  }

  public List<PersonDto> getAll() {
    return personRepository.findAll().stream()
        .map(personMapper::personToPersonDto)
        .collect(Collectors.toList());
  }

  public List<PersonDto> findPeople(final String username, final Integer age,
                                    final Long pesel, final boolean isOrConjunction) {
    return personSearchService.findBy(username, age, pesel, isOrConjunction).stream()
        .map(personMapper::personToPersonDto)
        .collect(Collectors.toList());
  }
}
