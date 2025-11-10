package com.example.Studentmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Studentmanagement.entity.Student;
import com.example.Studentmanagement.repository.StudentRepository;
import com.example.Studentmanagement.exception.StudentNotFoundException;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    // ✅ CREATE
    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    // ✅ READ - all students
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // ✅ READ - single student by ID
    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student not found with id " + id));
    }

    // ✅ UPDATE
    public Student updateStudent(Long id, Student updatedStudent) {
        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student not found with id " + id));

        existingStudent.setName(updatedStudent.getName());
        existingStudent.setEmail(updatedStudent.getEmail());
        existingStudent.setCourse(updatedStudent.getCourse());

        return studentRepository.save(existingStudent);
    }

    // ✅ DELETE
    public void deleteStudent(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student not found with id " + id));
        studentRepository.delete(student);
    }
}
