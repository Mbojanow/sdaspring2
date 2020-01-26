package pl.sdacademy.springdemo.model.mappers;

import org.mapstruct.Mapper;

import pl.sdacademy.springdemo.domain.Person;
import pl.sdacademy.springdemo.model.dto.PersonDto;

@Mapper
public interface PersonMapper {

  Person personDtoToPerson(PersonDto personDto);

  PersonDto personToPersonDto(Person person);
}
