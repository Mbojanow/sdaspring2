package pl.sdacademy.springdemo.bootstrap;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
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
  private final PasswordEncoder passwordEncoder;

  public InitialUsersBootstrap(final UserRepository userRepository, final RoleRepository roleRepository,
                               final PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.roleRepository = roleRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  @Transactional
  public void run(final String... args) throws Exception {
    final Role adminRole = roleRepository.save(new Role("ADMIN"));
    final var user = new User("admin", "admin@sda.pl", passwordEncoder.encode("Admin_123"),
        List.of(adminRole), List.of());
    userRepository.save(user);
  }
}
