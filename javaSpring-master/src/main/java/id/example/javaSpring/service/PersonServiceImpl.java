package id.example.javaSpring.service;

import id.example.javaSpring.model.dto.ErrorMessage;
import id.example.javaSpring.model.dto.ErrorSchema;
import id.example.javaSpring.model.dto.PersonDto;
import id.example.javaSpring.model.dto.SchemaDto;
import id.example.javaSpring.model.entity.Person;
import id.example.javaSpring.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class PersonServiceImpl implements PersonService{
    @Autowired
    PersonRepository personRepository;

    @Override
    public ResponseEntity<?> getAllPerson() {
        List<Person> person= personRepository.findAll();
        return ResponseEntity.ok().body(person);
    }

    @Override
    public ResponseEntity<?> getPerson(Integer id) {
        SchemaDto schemaDto = new SchemaDto();
        ErrorMessage errorMessage = new ErrorMessage();
        ErrorSchema errorSchema = new ErrorSchema();
        if (personRepository.findById(id).isPresent()) {
            Person person = personRepository.findById(id).get();

            errorMessage.setEnglish("Success");
            errorMessage.setIndonesian("Berhasil");

            errorSchema.setError_code(HttpStatus.ACCEPTED.toString());
            errorSchema.setError_message(errorMessage);

            schemaDto.setError_schema(errorSchema);
            schemaDto.setOutput_schema(person);
            return ResponseEntity.ok().body(schemaDto);
        }
        errorMessage.setEnglish("Failed");
        errorMessage.setIndonesian("Gagal");

        errorSchema.setError_code(HttpStatus.INTERNAL_SERVER_ERROR.toString());
        errorSchema.setError_message(errorMessage);

        schemaDto.setError_schema(errorSchema);
        return ResponseEntity.ok().body(schemaDto);
    }

    @Override
    public ResponseEntity<?> insertPerson(PersonDto dto) {
        SchemaDto schemaDto = new SchemaDto();
        ErrorMessage errorMessage = new ErrorMessage();
        ErrorSchema errorSchema = new ErrorSchema();

        try {
            Person person = new Person(dto.getFull_name(), dto.getAddress(), dto.getPhone_number());
            personRepository.save(person);

            errorMessage.setEnglish("Success");
            errorMessage.setIndonesian("Berhasil");

            errorSchema.setError_code(HttpStatus.ACCEPTED.toString());
            errorSchema.setError_message(errorMessage);

            schemaDto.setError_schema(errorSchema);
            schemaDto.setOutput_schema(person);
            return ResponseEntity.ok().body(schemaDto);
        } catch (Exception e) {
            errorMessage.setEnglish("Failed");
            errorMessage.setIndonesian("Gagal");

            errorSchema.setError_code(HttpStatus.INTERNAL_SERVER_ERROR.toString());
            errorSchema.setError_message(errorMessage);

            schemaDto.setError_schema(errorSchema);
            return ResponseEntity.ok().body(schemaDto);
        }
    }

    @Override
    public ResponseEntity<?> updatePerson(PersonDto dto, Integer id) {
        SchemaDto schemaDto = new SchemaDto();
        ErrorMessage errorMessage = new ErrorMessage();
        ErrorSchema errorSchema = new ErrorSchema();

        try {
            Person person = personRepository.findById(id).get();
            person.setFull_name(dto.getFull_name());
            personRepository.save(person);

            errorMessage.setEnglish("Success");
            errorMessage.setIndonesian("Berhasil");

            errorSchema.setError_code(HttpStatus.ACCEPTED.toString());
            errorSchema.setError_message(errorMessage);

            schemaDto.setError_schema(errorSchema);
            schemaDto.setOutput_schema(person);
            return ResponseEntity.ok().body(schemaDto);
        } catch (Exception e) {
            errorMessage.setEnglish("Failed");
            errorMessage.setIndonesian("Gagal");

            errorSchema.setError_code(HttpStatus.INTERNAL_SERVER_ERROR.toString());
            errorSchema.setError_message(errorMessage);

            schemaDto.setError_schema(errorSchema);
            return ResponseEntity.ok().body(schemaDto);
        }
    }

    @Override
    public ResponseEntity<?> deletePerson(Integer id) {
        SchemaDto schemaDto = new SchemaDto();
        ErrorMessage errorMessage = new ErrorMessage();
        ErrorSchema errorSchema = new ErrorSchema();

        try {
            Person person = personRepository.findById(id).get();
            person.setDeleted(true);
            personRepository.save(person);

            errorMessage.setEnglish("Success");
            errorMessage.setIndonesian("Berhasil");

            errorSchema.setError_code(HttpStatus.ACCEPTED.toString());
            errorSchema.setError_message(errorMessage);

            schemaDto.setError_schema(errorSchema);
            schemaDto.setOutput_schema(person);
            return ResponseEntity.ok().body(schemaDto);
        } catch (Exception e) {
            errorMessage.setEnglish("Failed");
            errorMessage.setIndonesian("Gagal");

            errorSchema.setError_code(HttpStatus.INTERNAL_SERVER_ERROR.toString());
            errorSchema.setError_message(errorMessage);

            schemaDto.setError_schema(errorSchema);
            return ResponseEntity.ok().body(schemaDto);
        }
    }
}
