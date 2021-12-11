package com.example.demo.student;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Service //sets this class as a Spring bean
public class StudentService {

    public List<Student> getStudents() {
        //return "Hello World!";
        return List.of(
                new Student(
                        1L,
                        "Miriam",
                        "miriam.jamal@gmail.com",
                        LocalDate.of(2000, Month.JANUARY, 5),
                        21
                )
        );
        //this returns student data as json in the web browser on port 8080
    }
}
