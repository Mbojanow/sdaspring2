package pl.sdacademy.springdemo.model;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserForm {

  @NotNull
  private String username;

  @NotNull
  private String email;

  private String password;

  private String confirmPassword;

  @AssertTrue
  public boolean isPasswordMatch() {
    return password != null && password.equals(confirmPassword);
  }
}
