package com.example.studentmanagement.service;

import com.example.studentmanagement.dto.StudentDto;
import com.example.studentmanagement.dto.CourseDto;

import java.util.List;

public interface StudentService {

    // CREATE
    StudentDto addStudent(StudentDto studentDto);

    // READ
    List<StudentDto> getAllStudents();

    StudentDto getStudentById(Long id);

    // UPDATE
    StudentDto updateStudent(Long id, StudentDto studentDto);

    // DELETE
    void deleteStudent(Long id);

    // COURSE: ADD COURSE TO STUDENT
    CourseDto addCourseToStudent(Long studentId, CourseDto courseDto);
}
