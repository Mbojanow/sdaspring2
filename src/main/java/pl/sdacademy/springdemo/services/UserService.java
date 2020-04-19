package pl.sdacademy.springdemo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.sdacademy.springdemo.configuration.RolesConfiguration;
import pl.sdacademy.springdemo.domain.Role;
import pl.sdacademy.springdemo.domain.User;
import pl.sdacademy.springdemo.model.UserForm;
import pl.sdacademy.springdemo.repositories.UserRepository;

@Service
@Transactional
public class UserService {

  private final UserRepository userRepository;
  private final RolesConfiguration rolesConfiguration;

  public UserService(final UserRepository userRepository, final RolesConfiguration rolesConfiguration) {
    this.userRepository = userRepository;
    this.rolesConfiguration = rolesConfiguration;
  }

  public List<User> getAllUsers() {
    final List<String> allRoles = rolesConfiguration.getRoles();
    return userRepository.findAll().stream()
        .map(user -> addPossibleRoles(user, allRoles))
        .collect(Collectors.toList());
  }

  private User addPossibleRoles(final User user, final List<String> allRoles) {
    final List<String> possibleRoles = new ArrayList<>();
    for (final String role : allRoles) {
      if (!user.getRoles().contains(new Role(role))) {
        possibleRoles.add(role);
      }
    }
    user.setPossibleRoles(possibleRoles);
    return user;
  }

  public User createUser(final UserForm userForm) {
    final User user = new User(userForm.getUsername(), userForm.getEmail(), userForm.getPassword(), List.of(), List.of());
    return userRepository.save(user);
  }

  public void deleteUser(final String username) {
    final Optional<User> userOptional = userRepository.findById(username);
    if (userOptional.isPresent()) {
      userRepository.deleteById(username);
    } else {
      throw new UserException("Cannot delete user that does not exit");
    }
  }
}
