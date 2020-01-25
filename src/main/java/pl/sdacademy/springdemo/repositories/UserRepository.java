package pl.sdacademy.springdemo.repositories;

import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;

import pl.sdacademy.springdemo.domain.User;

public interface UserRepository extends JpaRepository<User, String> {
  Long countAllByUsernameOrEmail(@NonNull String username, @NonNull String email);

  @Query("SELECT u FROM users u left join fetch u.roles WHERE u.username = :username")
  Optional<User> findByUsernameWithRoles(@Param("username")String username);
}
