package pl.sdacademy.springdemo.bootstrap;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import pl.sdacademy.springdemo.domain.Role;
import pl.sdacademy.springdemo.domain.User;
import pl.sdacademy.springdemo.repositories.RoleRepository;
import pl.sdacademy.springdemo.repositories.UserRepository;

@Component
public class InitialUsersBootstrap implements CommandLineRunner {

  private final UserRepository userRepository;
  private final RoleRepository roleRepository;

  public InitialUsersBootstrap(final UserRepository userRepository, final RoleRepository roleRepository) {
    this.userRepository = userRepository;
    this.roleRepository = roleRepository;
  }

  @Override
  @Transactional
  public void run(final String... args) throws Exception {
    final Role adminRole = roleRepository.save(new Role("ADMIN"));
    final var user = new User("admin", "admin@sda.pl", "Admin_123",
        List.of(adminRole), List.of());
    userRepository.save(user);
  }
}
