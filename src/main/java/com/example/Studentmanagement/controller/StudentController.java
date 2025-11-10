package com.example.Studentmanagement.controller;

import com.example.Studentmanagement.entity.Student;
import com.example.Studentmanagement.repository.StudentRepository;
import com.example.Studentmanagement.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/students") // ✅ Common base path for all student APIs
public class StudentController {

    @Autowired
    private StudentService studentService;

    // ✅ Add this repository only for temporary testing
    @Autowired
    private StudentRepository studentRepository;

    // --- General Info Endpoints ---

    @GetMapping("/home")
    public String home() {
        return "This is the Home Page";
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to Student Management API!";
    }

    // --- CRUD Endpoints ---

    // ✅ CREATE
    @PostMapping
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        Student createdStudent = studentService.addStudent(student);
        return ResponseEntity.ok(createdStudent);
    }

    // ✅ READ - all students
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }

    // ✅ READ - single student by ID
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        Student student = studentService.getStudentById(id);
        return ResponseEntity.ok(student);
    }

    // ✅ UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student student) {
        Student updatedStudent = studentService.updateStudent(id, student);
        return ResponseEntity.ok(updatedStudent);
    }

    // ✅ DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok("Student deleted successfully with ID: " + id);
    }

    // ✅ TEST DATABASE CONNECTION
    @PostMapping("/test")
    public ResponseEntity<Student> testDatabaseConnection() {
        Student s = new Student();
        s.setName("Rini");
        s.setEmail("rini@example.com");
        s.setCourse("Spring Boot");

        Student savedStudent = studentRepository.save(s);
        return ResponseEntity.ok(savedStudent);
    }
}

