package pl.sdacademy.springdemo.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "users")
public class User {

  @Id
  private String username;

  @Column(name = "email")
  private String email;

  @Column(name = "password")
  private String password;

  @OneToMany
  @JoinColumn(name = "user_id")
  private List<Role> roles;
}
