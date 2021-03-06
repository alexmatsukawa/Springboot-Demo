package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service //sets this class as a Spring bean
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired //Dependency Injection for Repository of Students
    public  StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    public List<Student> getStudents() {
        //return "Hello World!";
//        return List.of(
//                new Student(
//                        1L,
//                        "Miriam",
//                        "miriam.jamal@gmail.com",
//                        LocalDate.of(2000, Month.JANUARY, 5),
//                        21
//                )
//        );
        //this returns student data as json in the web browser on port 8080

        //better way of getting a list of all students compared to above code...
        return studentRepository.findAll(); //returns a list of all students in the db
        //magic of Spring JPA -> don't need to implement the interface methods for a Postgres repo; methods will work automatically
    }

    //new method checks to see if a student's email is present in the db system; if not, save the student; else send an error message
    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if(studentOptional.isPresent()) {
            throw new IllegalStateException("email already exists");
        }
        studentRepository.save(student);
        //System.out.println(student);
    }

    public void deleteStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        if(!exists) {
            throw new IllegalStateException("student with ID " + studentId + " does not exist");
        }
        studentRepository.deleteById(studentId);
    }

    @Transactional // this annotation tells the entity to go into a manage state (i.e. does not need queries)
    public void updateStudent(Long studentId, String name, String email) {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new IllegalStateException("" +
                "student with id " + studentId + " does not exist"));

        if (name != null && name.length() > 0 && !Objects.equals(student.getName(), name)) {
            student.setName(name);
        }

        if (email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)) {
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
            if(studentOptional.isPresent()) {
                throw new IllegalStateException("email already exists");
            }
            student.setEmail(email);
        }
    }
}
