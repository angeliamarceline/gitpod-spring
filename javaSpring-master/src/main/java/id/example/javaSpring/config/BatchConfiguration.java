package id.example.javaSpring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import id.example.javaSpring.model.entity.Person;
import id.example.javaSpring.processor.ProfilePersonItemProcessor;
import id.example.javaSpring.repository.PersonRepository;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
    @Autowired
    PersonRepository personRepository;

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public ItemReader<Person> profilePersonItemReader(DataSource dataSource) {
        JdbcCursorItemReader<Person> databaseReader = new JdbcCursorItemReader<>();
        databaseReader.setDataSource(dataSource);
        databaseReader.setSql("SELECT * FROM person_tbl");
        databaseReader.setRowMapper(new BeanPropertyRowMapper<>(Person.class));

        return databaseReader;
    }

    @Bean
    public ProfilePersonItemProcessor profilePersonItemProcessor() {
        return new ProfilePersonItemProcessor();
    }

    @Bean
    public RepositoryItemWriter<Person> writerProfilePerson() {
        RepositoryItemWriter<Person> writer = new RepositoryItemWriter<>();
        writer.setRepository(personRepository);
        writer.setMethodName("save");

        return writer;
    }

    @Bean
    public Step step1ProfilePerson(ItemReader<Person> databaseXmlItemReader,
            ItemProcessor<Person, Person> databaseXmlItemProcessor, ItemWriter<Person> databaseXmlItemWriter,
            StepBuilderFactory stepBuilderFactory) {
        return stepBuilderFactory.get("profilePersonItemReader").chunk(1).reader(databaseXmlItemReader).processor(databaseXmlItemProcessor).writer(databaseXmlItemWriter).build();
    }

    @Bean
    public Job profilePersonJob(JobBuilderFactory jobBuilderFactory, @Qualifier("step1ProfilePerson") Step step1ProfilePerson){
        return jobBuilderFactory.get("profilePersonJob").incrementer(new RunIdIncrementer()).flow(step1ProfilePerson).end().build();
    }

}
