package pl.sdacademy.springdemo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.sdacademy.springdemo.domain.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
  // SELECT * FROM roles WHERE name = :name
  Optional<Role> findByName(String name);
}
