package pl.sdacademy.springdemo;

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
@Transactional
public class UsersBootstraper implements CommandLineRunner {

  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final PasswordEncoder passwordEncoder;

  public UsersBootstraper(final UserRepository userRepository, final RoleRepository roleRepository,
                          final PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.roleRepository = roleRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public void run(final String... args) throws Exception {
    final Role role = new Role("ROLE_ADMIN");
    roleRepository.save(role);

    final Role roleUser = new Role("ROLE_USER");
    roleRepository.save(roleUser);

    final Role roleOther = new Role("ROLE_OTHER");
    roleRepository.save(roleOther);

    final User user = new User("admin", "admin@admins.com",
        passwordEncoder.encode("admin"), List.of(),
        List.of(role));
    userRepository.save(user);
  }
}
