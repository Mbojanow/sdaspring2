package pl.sdacademy.springdemo.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.sdacademy.springdemo.domain.User;
import pl.sdacademy.springdemo.exceptions.SdaException;
import pl.sdacademy.springdemo.model.UserForm;
import pl.sdacademy.springdemo.repositories.UserRepository;

@Service
@Transactional
public class UserService {

  private final UserRepository userRepository;
  private final UserMapper userMapper;

  public UserService(final UserRepository userRepository, final UserMapper userMapper) {
    this.userRepository = userRepository;
    this.userMapper = userMapper;
  }

  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  public User createUser(final UserForm userForm) {
    final Long usersCountWithDuplicatedData
        = userRepository.countAllByUsernameOrEmail(userForm.getUsername(), userForm.getEmail());
    if (usersCountWithDuplicatedData > 0) {
      throw new SdaException("User with same username or email already exists");
    }
    final User user = userMapper.userFormToUser(userForm);
    return userRepository.save(user);
  }

  public void deleteUser(final String username) {
    userRepository.deleteById(username);
  }
}
