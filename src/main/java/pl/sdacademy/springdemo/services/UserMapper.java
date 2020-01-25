package pl.sdacademy.springdemo.services;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import pl.sdacademy.springdemo.domain.User;
import pl.sdacademy.springdemo.model.UserForm;

@Component
public class UserMapper {

  private final PasswordEncoder passwordEncoder;

  public UserMapper(final PasswordEncoder passwordEncoder) {
    this.passwordEncoder = passwordEncoder;
  }

  public User userFormToUser(final UserForm userForm) {
    return new User(userForm.getUsername(), userForm.getEmail(),
        passwordEncoder.encode(userForm.getPassword()), List.of(), List.of());
  }
}
