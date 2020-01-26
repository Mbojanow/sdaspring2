package pl.sdacademy.springdemo.model.mappers;

import org.mapstruct.Mapper;

import pl.sdacademy.springdemo.domain.DrivingLicense;
import pl.sdacademy.springdemo.model.dto.DrivingLicenseDto;

@Mapper
public interface DrivingLicenseMapper {

  DrivingLicense drivingLicenseDtoToDrivingLicense(DrivingLicenseDto drivingLicenseDto);

  DrivingLicenseDto drivingLicenseToDrivingLicenseDto(DrivingLicense drivingLicense);
}
