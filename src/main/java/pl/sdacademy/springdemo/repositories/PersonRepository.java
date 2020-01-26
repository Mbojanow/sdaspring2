package pl.sdacademy.springdemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.sdacademy.springdemo.domain.Person;

//MongoRepository
public interface PersonRepository extends JpaRepository<Person, String> {
}
