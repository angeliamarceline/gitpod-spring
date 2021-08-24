package id.example.javaSpring.controller;

import id.example.javaSpring.model.dto.*;
import id.example.javaSpring.model.entity.Person;
import id.example.javaSpring.model.entity.Province;
import id.example.javaSpring.repository.PersonRepository;
import id.example.javaSpring.service.PersonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonServiceImpl personService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllPerson() {
        return personService.getAllPerson();
    }

    @GetMapping("get/{id}")
    public ResponseEntity<?> getPerson(@PathVariable Integer id) {
        return personService.getPerson(id);
    }

    @PostMapping("/insert")
    public ResponseEntity<?> insertPerson(@RequestBody PersonDto dto) {
        return personService.insertPerson(dto);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updatePerson(@RequestBody PersonDto dto, @PathVariable Integer id) {
        return personService.updatePerson(dto, id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePerson(@PathVariable Integer id) {
       return personService.deletePerson(id);
    }
}
