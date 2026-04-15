package com.example.studentapi.repository;

import com.example.studentapi.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * =============================================
 *  REPOSITORY (Data Access Layer)
 *
 *  JpaRepository<Student, Long> gives you FREE:
 *    save(), findById(), findAll(),
 *    deleteById(), count(), existsById() …
 *
 *  You can also declare custom queries just by
 *  naming the method correctly (Spring Data
 *  derives the SQL automatically).
 * =============================================
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    // Spring Data auto-generates: SELECT * FROM students WHERE email = ?
    Optional<Student> findByEmail(String email);

    // SELECT * FROM students WHERE grade = ?
    List<Student> findByGrade(int grade);

    // SELECT * FROM students WHERE first_name LIKE %name% OR last_name LIKE %name%
    List<Student> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(
            String firstName, String lastName);
}
