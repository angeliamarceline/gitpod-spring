package id.example.javaSpring.repository;

import id.example.javaSpring.model.entity.Person;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

    void save(List<? extends Person> persons);
}
