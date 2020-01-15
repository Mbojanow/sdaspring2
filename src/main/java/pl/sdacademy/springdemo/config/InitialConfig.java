package pl.sdacademy.springdemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.sdacademy.springdemo.domain.Role;
import pl.sdacademy.springdemo.repositories.RoleRepository;

@Component
public class InitialConfig implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    @Transactional
    public void run(final String... args) {
        roleRepository.save(new Role("USER"));
        roleRepository.save(new Role("ADMIN"));
    }
}
