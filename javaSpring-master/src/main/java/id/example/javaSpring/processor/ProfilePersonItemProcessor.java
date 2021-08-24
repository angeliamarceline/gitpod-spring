package id.example.javaSpring.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import id.example.javaSpring.model.dto.PersonDto;
import id.example.javaSpring.repository.PersonRepository;

public class ProfilePersonItemProcessor implements ItemProcessor<PersonDto, PersonDto> {
    private static final Logger log = LoggerFactory.getLogger(ProfilePersonItemProcessor.class);

    @Autowired
    PersonRepository personRepository;

    @Override
    public PersonDto process(PersonDto person) throws Exception {
        final String full_name = person.getFull_name();
        final String address = person.getAddress();
        final String phone_number = person.getPhone_number();

        PersonDto find = personRepository.findByFull_name(full_name);

        if (find != null) {
            log.info(String.format("%s already found", full_name));
            return null;
        } else {
            final PersonDto transformedPerson = new PersonDto(full_name, address, phone_number);
            return transformedPerson;
        }

    }

    
}
