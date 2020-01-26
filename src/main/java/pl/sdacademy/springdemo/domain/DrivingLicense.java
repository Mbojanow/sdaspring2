package pl.sdacademy.springdemo.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "driving_licenses")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DrivingLicense {
//idNumber (Long), receiveDate(Date), valid thru(Date),
//category (Enum, A/B/C/D), address
  @Id
  private Long idNumber;

  @Column(name = "receive_date")
  private Date receiveDate;

  @Column(name = "valid_thru")
  private Date validThru;

  @Column(name = "address")
  private String address;

  @Column(name = "category")
  @Enumerated(EnumType.STRING)
  private DriveLicenseCategory category;
}
