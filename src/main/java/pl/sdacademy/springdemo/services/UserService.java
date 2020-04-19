package pl.sdacademy.springdemo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.sdacademy.springdemo.configuration.RolesConfiguration;
import pl.sdacademy.springdemo.domain.Role;
import pl.sdacademy.springdemo.domain.User;
import pl.sdacademy.springdemo.model.UserForm;
import pl.sdacademy.springdemo.repositories.RoleRepository;
import pl.sdacademy.springdemo.repositories.UserRepository;

@Service
@Transactional
public class UserService {

  private final UserRepository userRepository;
  private final RolesConfiguration rolesConfiguration;
  private final RoleRepository roleRepository;
  private final PasswordEncoder passwordEncoder;

  public UserService(final UserRepository userRepository, final RolesConfiguration rolesConfiguration,
                     final RoleRepository roleRepository/*, final PasswordEncoder passwordEncoder*/) {
    this.userRepository = userRepository;
    this.rolesConfiguration = rolesConfiguration;
    this.roleRepository = roleRepository;
    this.passwordEncoder = null;
  }

  public User getUserByUsername(final String username) {
    return userRepository.findById(username)
        .orElseThrow(() -> new UserException("User with name " + username + " does not exist"));
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
      if (!user.getRoles().stream()
          .map(Role::getName)
          .collect(Collectors.toList())
          .contains(role)) {
        possibleRoles.add(role);
      }
    }
    user.setPossibleRoles(possibleRoles);
    return user;
  }

  public User createUser(final UserForm userForm) {
    final User user = new User(userForm.getUsername(), userForm.getEmail(),
        passwordEncoder.encode(userForm.getPassword()), List.of(), List.of());
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

  public void addRoleToUser(final String username, final String rolename) {
    final User existingUser = validateRoleForUser(username, rolename);

    final Role savedRole = roleRepository.save(new Role(rolename));
    existingUser.getRoles().add(savedRole);
    userRepository.save(existingUser);
  }

  private User validateRoleForUser(final String username, final String rolename) {
    final User existingUser = userRepository.findById(username)
        .orElseThrow(() -> new UserException("User with username " + username + " does not exist"));
    if (!rolesConfiguration.getRoles().contains(rolename)) {
      throw new UserException("Role with name " + rolename + " is undefined");
    }
    if (existingUser.getRoles().stream().anyMatch(role -> role.getName().equals(rolename))) {
      throw new UserException("User " + username + " has role " + rolename + " already assigned");
    }
    return existingUser;
  }
}
