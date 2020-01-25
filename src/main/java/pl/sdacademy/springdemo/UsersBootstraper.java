package pl.sdacademy.springdemo;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import pl.sdacademy.springdemo.domain.User;
import pl.sdacademy.springdemo.repositories.UserRepository;

@Component
@Transactional
public class UsersBootstraper implements CommandLineRunner {

  private final UserRepository userRepository;

  public UsersBootstraper(final UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public void run(final String... args) throws Exception {
    final User user = new User("admin", "admin@admins.com", "admin", List.of());
    userRepository.save(user);
  }
}
