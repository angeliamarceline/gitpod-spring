package id.example.javaSpring.repository;

import id.example.javaSpring.model.dto.PersonDto;
import id.example.javaSpring.model.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

    PersonDto findByFull_name(String full_name);
}
