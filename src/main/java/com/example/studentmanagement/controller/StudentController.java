package com.example.studentmanagement.controller;

import com.example.studentmanagement.dto.StudentDto;
import com.example.studentmanagement.dto.CourseDto;
import com.example.studentmanagement.service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;


    // ---------------------------
    // BASIC ENDPOINTS
    // ---------------------------

    @GetMapping("/home")
    public String home() {
        return "This is the Home Page";
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to Student Management API!";
    }


    // ---------------------------
    // CRUD ENDPOINTS (DTO)
    // ---------------------------

    // CREATE STUDENT
    @PostMapping
    public ResponseEntity<StudentDto> addStudent(@RequestBody StudentDto studentDto) {
        StudentDto created = studentService.addStudent(studentDto);
        return ResponseEntity.ok(created);
    }


    // GET ALL STUDENTS
    @GetMapping
    public ResponseEntity<List<StudentDto>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }


    // GET STUDENT BY ID
    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }


    // UPDATE STUDENT
    @PutMapping("/{id}")
    public ResponseEntity<StudentDto> updateStudent(
            @PathVariable Long id,
            @RequestBody StudentDto studentDto) {

        StudentDto updated = studentService.updateStudent(id, studentDto);
        return ResponseEntity.ok(updated);
    }


    // DELETE STUDENT
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok("Student deleted successfully with ID: " + id);
    }


    // ---------------------------
    // COURSE MANAGEMENT
    // ---------------------------

    // ADD COURSE TO STUDENT
    @PostMapping("/{id}/courses")
    public ResponseEntity<CourseDto> addCourseToStudent(
            @PathVariable Long id,
            @RequestBody CourseDto courseDto) {

        CourseDto added = studentService.addCourseToStudent(id, courseDto);
        return ResponseEntity.ok(added);
    }
}
