package pl.sdacademy.springdemo.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.sdacademy.springdemo.domain.User;
import pl.sdacademy.springdemo.exceptions.GenericException;
import pl.sdacademy.springdemo.repositories.RoleRepository;
import pl.sdacademy.springdemo.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserService(final UserRepository userRepository, final RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public void create(final User user) {
        final Optional<User> existingUser = userRepository.findById(user.getUsername());
        if (existingUser.isPresent()) {
            throw new GenericException("user already exists");
        }
        userRepository.save(user) ;
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User findByName(final String name) {
        return userRepository.findById(name).orElseThrow(() -> new GenericException("User with given name does not exist"));
    }

    public void update(final String username, final User updatedUser) {
        userRepository.findById(username).ifPresent(user ->  {
            user.setPassword(updatedUser.getPassword());
            userRepository.save(user);
        });
    }

    public void delete(final String userName) {
        userRepository.deleteById(userName);
    }

    public void addRole(final String username, final String role) {
        userRepository.findById(username).ifPresent(user -> {
            user.getRoles().add(roleRepository.getOne(role));
            userRepository.save(user);
        });
    }
}
