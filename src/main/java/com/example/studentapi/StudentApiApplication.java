package com.example.studentapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * =============================================
 *  ENTRY POINT
 *  @SpringBootApplication = @Configuration
 *                          + @EnableAutoConfiguration
 *                          + @ComponentScan
 * =============================================
 */
@SpringBootApplication
public class StudentApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentApiApplication.class, args);
        System.out.println("✅  Student API is running → http://localhost:8080/api/students");
    }
}
