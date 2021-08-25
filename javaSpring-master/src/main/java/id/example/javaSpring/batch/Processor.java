package id.example.javaSpring.batch;

import java.util.Map;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import id.example.javaSpring.model.entity.Person;

@Component
public class Processor implements ItemProcessor<Person, Person>{

    @Override
    public Person process(Person person) throws Exception {
        
        return person;
    }
    
}
