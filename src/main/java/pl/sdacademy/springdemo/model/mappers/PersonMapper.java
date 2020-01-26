package pl.sdacademy.springdemo.model.mappers;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import pl.sdacademy.springdemo.domain.Person;
import pl.sdacademy.springdemo.model.dto.PersonDto;

@Mapper(uses = DrivingLicenseMapper.class, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface PersonMapper {

  @Mapping(target = "drivingLicense", source = "drivingLicenseDto")
  @Mapping(target = "createTime", ignore = true)
  @Mapping(target = "firstName", source = "fn")
  @Mapping(target = "lastName", source = "ln")
  Person personDtoToPerson(PersonDto personDto);

  @InheritInverseConfiguration
  PersonDto personToPersonDto(Person person);
}
