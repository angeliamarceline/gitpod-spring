package id.example.javaSpring.batch;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import id.example.javaSpring.model.entity.Person;
import id.example.javaSpring.repository.PersonRepository;

@Component
public class DBWriter implements ItemWriter<Person> {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public void write(List<? extends Person> persons) throws Exception {
        System.out.println("Data Saved for Persons: " + persons);
        personRepository.save(persons);
        
    }
    
}
