package pl.sdacademy.springdemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserForm {
  private String username;
  private String email;
  private String password;
  private String confirmPassword;
}
