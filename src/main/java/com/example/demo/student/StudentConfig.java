package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static java.time.Month.*;

@Configuration //denotes this as a configuration file...
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {//Dependency Injection for the StudentRepo
        return args -> {
            Student miriam = new Student(
                    "Miriam",
                    "miriam.jamal@gmail.com",
                    LocalDate.of(2000, JANUARY, 5),
                    21
            );
            //No longer need ID field since the DB will auto-generate it for us when adding a new student
            Student alex = new Student(
                    "Alex",
                    "amats96@gmail.com",
                    LocalDate.of(1999, JULY, 22),
                    22
            );

            repository.saveAll(
                    List.of(miriam, alex)
            );
        };
    }
}
