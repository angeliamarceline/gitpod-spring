package id.example.javaSpring.model.processor;

import id.example.javaSpring.model.entity.People;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.batch.item.ItemProcessor;

public class PeopleItemProcessor implements ItemProcessor<People, People> {

    private static final Logger log = LoggerFactory.getLogger(PeopleItemProcessor.class);

    @Override
    public People process(final People people) throws Exception {
        final String firstName = people.getFirstName().toUpperCase();
        final String lastName = people.getLastName().toUpperCase();

        final People transformedPeople = new People(firstName, lastName);

        log.info("Converting (" + people + ") into (" + transformedPeople + ")");

        return transformedPeople;
    }

}
