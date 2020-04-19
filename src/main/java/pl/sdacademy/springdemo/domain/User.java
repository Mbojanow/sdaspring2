package pl.sdacademy.springdemo.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {

  @Id
  private String username;

  @Column(name = "email")
  private String email;

  @Column(name = "password")
  private String password;

  @OneToMany(fetch = FetchType.EAGER/*, cascade = { CascadeType.MERGE, CascadeType.PERSIST }*/)
  @JoinColumn(name = "user_id")
  private List<Role> roles;

  @Transient
  // adnotacja powoduje że to pole jest IGNOROWANE przez HIBERNATE
  // NIE jest zapisywane do bazy danych
  // HIBERNATE NIE generuje kolumny dla tego pola
  private List<String> possibleRoles;
}
