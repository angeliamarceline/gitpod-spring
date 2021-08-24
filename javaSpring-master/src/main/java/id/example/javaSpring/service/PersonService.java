package id.example.javaSpring.service;

import id.example.javaSpring.model.dto.PersonDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

public interface PersonService {
    public ResponseEntity<?> getAllPerson();
    public ResponseEntity<?> getPerson(Integer id);
    public ResponseEntity<?> insertPerson(PersonDto dto);
    public ResponseEntity<?> updatePerson(PersonDto dto, Integer id);
    public ResponseEntity<?> deletePerson(Integer id);
}
