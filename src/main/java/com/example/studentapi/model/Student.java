package com.example.studentapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

/**
 * =============================================
 *  MODEL (Entity)
 *  @Entity   → JPA maps this class to a DB table
 *  @Table    → customise the table name
 *  @Id       → primary key
 *  @GeneratedValue → auto-increment id
 * =============================================
 */
@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "First name is required")
    @Column(nullable = false)
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Column(nullable = false)
    private String lastName;

    @Email(message = "Must be a valid email")
    @NotBlank(message = "Email is required")
    @Column(nullable = false, unique = true)
    private String email;

    @Min(value = 1, message = "Grade must be at least 1")
    @Max(value = 12, message = "Grade cannot exceed 12")
    private int grade;

    // ── Constructors ──────────────────────────────
    public Student() {}

    public Student(String firstName, String lastName, String email, int grade) {
        this.firstName = firstName;
        this.lastName  = lastName;
        this.email     = email;
        this.grade     = grade;
    }

    // ── Getters & Setters ─────────────────────────
    public Long   getId()        { return id; }
    public void   setId(Long id) { this.id = id; }

    public String getFirstName()             { return firstName; }
    public void   setFirstName(String fn)    { this.firstName = fn; }

    public String getLastName()              { return lastName; }
    public void   setLastName(String ln)     { this.lastName = ln; }

    public String getEmail()                 { return email; }
    public void   setEmail(String email)     { this.email = email; }

    public int    getGrade()                 { return grade; }
    public void   setGrade(int grade)        { this.grade = grade; }

    @Override
    public String toString() {
        return "Student{id=" + id + ", name=" + firstName + " " + lastName
                + ", email=" + email + ", grade=" + grade + "}";
    }
}
