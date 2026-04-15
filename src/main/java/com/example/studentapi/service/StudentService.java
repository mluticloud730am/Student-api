package com.example.studentapi.service;

import com.example.studentapi.model.Student;
import com.example.studentapi.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * =============================================
 *  SERVICE (Business Logic Layer)
 *
 *  @Service marks this as a Spring-managed bean.
 *  Controllers call Service → Service calls Repository.
 *  Keep business rules here, NOT in controllers.
 * =============================================
 */
@Service
public class StudentService {

    private final StudentRepository repo;

    // Constructor injection (preferred over @Autowired on field)
    @Autowired
    public StudentService(StudentRepository repo) {
        this.repo = repo;
    }

    /** Get all students */
    public List<Student> getAllStudents() {
        return repo.findAll();
    }

    /** Get one student by id, or throw if not found */
    public Student getStudentById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
    }

    /** Create a new student */
    public Student createStudent(Student student) {
        // Business rule: email must be unique
        if (repo.findByEmail(student.getEmail()).isPresent()) {
            throw new RuntimeException("Email already in use: " + student.getEmail());
        }
        return repo.save(student);
    }

    /** Update an existing student */
    public Student updateStudent(Long id, Student updated) {
        Student existing = getStudentById(id);      // throws if not found
        existing.setFirstName(updated.getFirstName());
        existing.setLastName(updated.getLastName());
        existing.setEmail(updated.getEmail());
        existing.setGrade(updated.getGrade());
        return repo.save(existing);
    }

    /** Delete a student */
    public void deleteStudent(Long id) {
        getStudentById(id);                         // throws if not found
        repo.deleteById(id);
    }

    /** Search by name */
    public List<Student> searchStudents(String name) {
        return repo.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(name, name);
    }

    /** Filter by grade */
    public List<Student> getStudentsByGrade(int grade) {
        return repo.findByGrade(grade);
    }
}
