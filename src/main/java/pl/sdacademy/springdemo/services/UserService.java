package pl.sdacademy.springdemo.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.sdacademy.springdemo.domain.User;
import pl.sdacademy.springdemo.model.UserForm;
import pl.sdacademy.springdemo.repositories.UserRepository;

@Service
@Transactional
public class UserService {

  private final UserRepository userRepository;

  public UserService(final UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  public User createUser(final UserForm userForm) {
    final User user = new User(userForm.getUsername(), userForm.getEmail(), userForm.getPassword(), List.of());
    return userRepository.save(user);
  }

  public void deleteUser(final String username) {

  }
}
