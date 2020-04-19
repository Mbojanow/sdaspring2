package pl.sdacademy.springdemo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity(name = "roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {

  public Role(final String name) {
    this.name = name;
  }

  @Id
  @GeneratedValue
  private Long id;

  private String name;
}
