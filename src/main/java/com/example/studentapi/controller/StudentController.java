package com.example.studentapi.controller;

import com.example.studentapi.model.Student;
import com.example.studentapi.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * =============================================
 *  CONTROLLER (Presentation / API Layer)
 *
 *  @RestController = @Controller + @ResponseBody
 *    → every method returns JSON automatically
 *
 *  @RequestMapping → base URL for all endpoints
 *
 *  HTTP Methods:
 *    GET    → read data
 *    POST   → create data
 *    PUT    → update data
 *    DELETE → remove data
 * =============================================
 */
@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService service;

    @Autowired
    public StudentController(StudentService service) {
        this.service = service;
    }

    // ── GET /api/students  →  list all ───────────────────────────────────
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) Integer grade) {

        if (search != null) {
            return ResponseEntity.ok(service.searchStudents(search));
        }
        if (grade != null) {
            return ResponseEntity.ok(service.getStudentsByGrade(grade));
        }
        return ResponseEntity.ok(service.getAllStudents());
    }

    // ── GET /api/students/{id}  →  single student ────────────────────────
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable Long id) {
        return ResponseEntity.ok(service.getStudentById(id));
    }

    // ── POST /api/students  →  create ────────────────────────────────────
    @PostMapping
    public ResponseEntity<Student> createStudent(@Valid @RequestBody Student student) {
        Student created = service.createStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // ── PUT /api/students/{id}  →  update ────────────────────────────────
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(
            @PathVariable Long id,
            @Valid @RequestBody Student student) {
        return ResponseEntity.ok(service.updateStudent(id, student));
    }

    // ── DELETE /api/students/{id}  →  remove ─────────────────────────────
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        service.deleteStudent(id);
        return ResponseEntity.noContent().build();  // 204 No Content
    }
}
