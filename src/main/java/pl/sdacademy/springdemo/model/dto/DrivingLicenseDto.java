package pl.sdacademy.springdemo.model.dto;

import java.util.Date;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.sdacademy.springdemo.domain.DriveLicenseCategory;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DrivingLicenseDto {

  @JsonProperty("id_number")
  private Long idNumber;

  @JsonProperty("receive_date")
  private Date receiveDate;

  @JsonProperty("valid_thru")
  private Date validThru;

  @JsonProperty("address")
  private String address;

  @JsonProperty("category")
  private DriveLicenseCategory category;
  
}
