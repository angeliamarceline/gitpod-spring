package id.example.javaSpring;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableBatchProcessing
public class JavaSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaSpringApplication.class, args);
	}

//	public static void main(String[] args) throws Exception {
//		System.exit(SpringApplication.exit(SpringApplication.run(JavaSpringApplication.class, args)));
//	}
}
