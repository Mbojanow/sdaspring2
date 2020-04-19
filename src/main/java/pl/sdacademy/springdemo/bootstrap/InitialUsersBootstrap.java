package pl.sdacademy.springdemo.bootstrap;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import pl.sdacademy.springdemo.domain.User;
import pl.sdacademy.springdemo.repositories.UserRepository;

@Component
public class InitialUsersBootstrap implements CommandLineRunner {

  private final UserRepository userRepository;

  public InitialUsersBootstrap(final UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  @Transactional
  public void run(final String... args) throws Exception {
    final var user = new User("admin", "admin@sda.pl", "Admin_123", List.of());
    userRepository.save(user);
  }
}
