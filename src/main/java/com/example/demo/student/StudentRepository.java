package com.example.demo.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
//responsible for data access
public interface StudentRepository extends JpaRepository<Student, Long> {

        @Query("SELECT s FROM Student s WHERE s.email = ?1") //-> can do either this way or the way below
        Optional<Student> findStudentByEmail(String email);

}
