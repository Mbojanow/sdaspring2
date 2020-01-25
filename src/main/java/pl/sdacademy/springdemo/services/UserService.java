package pl.sdacademy.springdemo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.sdacademy.springdemo.domain.Role;
import pl.sdacademy.springdemo.domain.User;
import pl.sdacademy.springdemo.exceptions.SdaException;
import pl.sdacademy.springdemo.model.UserForm;
import pl.sdacademy.springdemo.repositories.UserRepository;

@Service
@Transactional
public class UserService {

  private final UserRepository userRepository;
  private final UserMapper userMapper;
  private final RoleService roleService;

  public UserService(final UserRepository userRepository, final UserMapper userMapper, final RoleService roleService) {
    this.userRepository = userRepository;
    this.userMapper = userMapper;
    this.roleService = roleService;
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

  public void addRoleToUser(final String username, final String roleName) {
    final Role role = roleService.getByName(roleName);
    final Optional<User> userOptional = userRepository.findById(username);
    if (userOptional.isPresent()) {
      final User user = userOptional.get();
      if (!user.getRoles().contains(role)) {
        user.getRoles().add(role);
        userRepository.save(user);
      }
    }
  }

  public void updateUser(final UserForm userForm, final String username) {
    if (!userForm.getUsername().equals(username)) {
      throw new SdaException("You filthy hacker! That's not the same user!");
    }
    final User user = userRepository.findById(username)
        .orElseThrow(() -> new SdaException("Cannot update non existing user"));
    user.setEmail(userForm.getEmail());
    user.setPassword(userForm.getPassword());
    userRepository.save(user);
  }
}
