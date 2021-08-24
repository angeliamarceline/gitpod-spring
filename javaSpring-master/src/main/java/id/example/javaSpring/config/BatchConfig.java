package id.example.javaSpring.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchConfig {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    public Step firstSpringBatchStep() {
        return stepBuilderFactory.get("firstSpringBatchStep").tasklet((StepContribution contribution, ChunkContext chunckContext) -> {System.out.println("My First Spring Batch Job"); return RepeatStatus.FINISHED;}).build();
    }

    @Bean
    public Job firstSpringBatchJob() {
        return jobBuilderFactory.get("firstSpringBatch").start(firstSpringBatchStep()).build();
    }
}
