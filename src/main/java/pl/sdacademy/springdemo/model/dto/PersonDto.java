package pl.sdacademy.springdemo.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonDto {

  @JsonProperty("username")
  private String username;

  @JsonProperty("first_name")
  private String fn;

  @JsonProperty("last_name")
  private String ln;

  @JsonProperty("age")
  private Integer age;

  @JsonProperty("pesel")
  private String pesel;

  @JsonProperty("driving_license")
  private DrivingLicenseDto drivingLicenseDto;
}
