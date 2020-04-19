package pl.sdacademy.springdemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.sdacademy.springdemo.domain.Role;

public interface RoleRepository extends JpaRepository<Role, String> {
}
