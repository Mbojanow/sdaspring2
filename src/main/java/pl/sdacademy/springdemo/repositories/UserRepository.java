package pl.sdacademy.springdemo.repositories;

import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import pl.sdacademy.springdemo.domain.User;

public interface UserRepository extends JpaRepository<User, String> {
  Long countAllByUsernameOrEmail(@NonNull String username, @NonNull String email);
}
