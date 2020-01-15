package pl.sdacademy.springdemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sdacademy.springdemo.domain.User;

public interface UserRepository extends JpaRepository<User, String> {
}
