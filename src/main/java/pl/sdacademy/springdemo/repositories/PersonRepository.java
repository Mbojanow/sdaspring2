package pl.sdacademy.springdemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.sdacademy.springdemo.domain.Person;

public interface PersonRepository extends JpaRepository<Person, String> {
}
