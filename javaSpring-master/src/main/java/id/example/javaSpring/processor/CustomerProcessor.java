package id.example.javaSpring.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import id.example.javaSpring.model.entity.Customer;

public class CustomerProcessor implements ItemProcessor<Customer, Customer> {
    // Creates a logger
    private static final Logger logger = LoggerFactory.getLogger(CustomerProcessor.class);
    // This method transforms data form one form to another.
    @Override
    public Customer process(final Customer customer) throws Exception {
        final String firstName = customer.getFirstName().toUpperCase();
        final String lastName = customer.getLastName().toUpperCase();
        // Creates a new instance of Person
        final Customer transformedCustomer = new Customer(1L, firstName, lastName);
        // logs the person entity to the application logs
        logger.info("Converting (" + customer + ") into (" + transformedCustomer + ")");
        return transformedCustomer;
    }
}
