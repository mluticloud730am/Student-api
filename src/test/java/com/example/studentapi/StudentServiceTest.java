package com.example.studentapi;

import com.example.studentapi.model.Student;
import com.example.studentapi.repository.StudentRepository;
import com.example.studentapi.service.StudentService;
import org.junit.jupiter.api.*;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * =============================================
 *  UNIT TESTS
 *
 *  @Mock       → creates a fake/stub repository
 *  @InjectMocks → injects mocks into the service
 *
 *  We test the SERVICE logic in isolation —
 *  no real DB, no HTTP calls needed.
 * =============================================
 */
@SpringBootTest
class StudentServiceTest {

    @Mock
    private StudentRepository repository;

    @InjectMocks
    private StudentService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should return all students")
    void testGetAllStudents() {
        List<Student> mockList = List.of(
            new Student("Alice", "Smith", "alice@test.com", 10),
            new Student("Bob",   "Jones", "bob@test.com",   11)
        );
        when(repository.findAll()).thenReturn(mockList);

        List<Student> result = service.getAllStudents();

        assertEquals(2, result.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should throw when student not found")
    void testGetStudentByIdNotFound() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(
            RuntimeException.class,
            () -> service.getStudentById(99L)
        );
        assertTrue(ex.getMessage().contains("99"));
    }

    @Test
    @DisplayName("Should create student successfully")
    void testCreateStudent() {
        Student s = new Student("Aarav", "Sharma", "aarav@test.com", 10);
        when(repository.findByEmail("aarav@test.com")).thenReturn(Optional.empty());
        when(repository.save(s)).thenReturn(s);

        Student result = service.createStudent(s);

        assertNotNull(result);
        assertEquals("Aarav", result.getFirstName());
    }

    @Test
    @DisplayName("Should reject duplicate email")
    void testCreateStudentDuplicateEmail() {
        Student s = new Student("Aarav", "Sharma", "aarav@test.com", 10);
        when(repository.findByEmail("aarav@test.com")).thenReturn(Optional.of(s));

        assertThrows(RuntimeException.class, () -> service.createStudent(s));
    }
}
