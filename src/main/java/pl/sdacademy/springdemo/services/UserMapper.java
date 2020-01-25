package pl.sdacademy.springdemo.services;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.Data;
import pl.sdacademy.springdemo.domain.User;
import pl.sdacademy.springdemo.model.UserForm;

@Component
public class UserMapper {

  public User userFormToUser(final UserForm userForm) {
    return new User(userForm.getUsername(), userForm.getEmail(),
        userForm.getPassword(), List.of(), List.of());
  }
}
