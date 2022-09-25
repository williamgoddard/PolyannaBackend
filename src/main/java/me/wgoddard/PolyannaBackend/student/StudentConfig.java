package me.wgoddard.PolyannaBackend.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repo) {
        return args -> {
            Student mariam = new Student(1l, "Mariam", "mariam@a.b", LocalDate.of(2000, 2, 1));
            repo.saveAll(List.of(mariam));
        };
    }

}
