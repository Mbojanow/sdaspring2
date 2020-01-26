package pl.sdacademy.springdemo.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "persons")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {

  @Id
  @Column(name = "username")
  private String username;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @Column(name = "age")
  private Integer age;

  @Column(name = "pesel")
  private Long pesel;

  @CreationTimestamp
  private Date createTime;

  @OneToOne
  @JoinColumn(name = "license_id")
  private DrivingLicense drivingLicense;
}
