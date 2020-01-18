package pl.sdacademy.springdemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.sdacademy.springdemo.domain.Role;
import pl.sdacademy.springdemo.domain.User;
import pl.sdacademy.springdemo.repositories.RoleRepository;
import pl.sdacademy.springdemo.repositories.UserRepository;

import java.util.List;

@Component
public class InitialConfig implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(final String... args) {
        roleRepository.save(new Role("USER"));
        final Role admin = roleRepository.save(new Role("ADMIN"));
        userRepository.save(new User("admin", passwordEncoder.encode("admin")));
    }
}
