package pl.sdacademy.springdemo.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

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

  @Transient
  private List<Role> nonAssignedRoles;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "user_to_roles",
    joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "username"),
    inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "name"))
  private List<Role> roles;
}
