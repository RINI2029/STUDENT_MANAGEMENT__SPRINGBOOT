package com.example.studentmanagement.service.impl;

import com.example.studentmanagement.dto.StudentDto;
import com.example.studentmanagement.dto.CourseDto;
import com.example.studentmanagement.entity.Student;
import com.example.studentmanagement.entity.Course;
import com.example.studentmanagement.exception.EmailAlreadyExistsException;
import com.example.studentmanagement.exception.StudentNotFoundException;
import com.example.studentmanagement.repository.StudentRepository;
import com.example.studentmanagement.repository.CourseRepository;
import com.example.studentmanagement.service.StudentService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private ModelMapper modelMapper;

    // ------------------------
    // CREATE STUDENT
    // ------------------------
    @Override
    public StudentDto addStudent(StudentDto studentDto) {

        Student student = modelMapper.map(studentDto, Student.class);

        // Check duplicate email
        if (studentRepository.existsByEmail(student.getEmail())) {
            throw new EmailAlreadyExistsException("Email already exists: " + student.getEmail());
        }

        Student saved = studentRepository.save(student);
        return modelMapper.map(saved, StudentDto.class);
    }

    // ------------------------
    // GET ALL STUDENTS
    // ------------------------
    @Override
    public List<StudentDto> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(student -> modelMapper.map(student, StudentDto.class))
                .collect(Collectors.toList());
    }

    // ------------------------
    // GET STUDENT BY ID
    // ------------------------
    @Override
    public StudentDto getStudentById(Long id) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() ->
                        new StudentNotFoundException("Student not found with ID: " + id)
                );

        return modelMapper.map(student, StudentDto.class);
    }

    // ------------------------
    // UPDATE STUDENT
    // ------------------------
    @Override
    public StudentDto updateStudent(Long id, StudentDto updatedDto) {

        Student existing = studentRepository.findById(id)
                .orElseThrow(() ->
                        new StudentNotFoundException("Student not found with ID: " + id)
                );

        // Check email used by another
        if (studentRepository.existsByEmailAndIdNot(updatedDto.getEmail(), id)) {
            throw new EmailAlreadyExistsException("Email already used: " + updatedDto.getEmail());
        }

        // Update fields
        existing.setName(updatedDto.getName());
        existing.setEmail(updatedDto.getEmail());
        existing.setCourse(updatedDto.getCourse());

        Student saved = studentRepository.save(existing);
        return modelMapper.map(saved, StudentDto.class);
    }

    // ------------------------
    // DELETE STUDENT
    // ------------------------
    @Override
    public void deleteStudent(Long id) {

        if (!studentRepository.existsById(id)) {
            throw new StudentNotFoundException("Student not found with ID: " + id);
        }

        studentRepository.deleteById(id);
    }

    // ------------------------
    // ADD COURSE TO STUDENT
    // ------------------------
    @Override
    public CourseDto addCourseToStudent(Long studentId, CourseDto courseDto) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() ->
                        new StudentNotFoundException("Student not found with ID: " + studentId)
                );

        Course course = modelMapper.map(courseDto, Course.class);
        course.setStudent(student);

        Course saved = courseRepository.save(course);
        return modelMapper.map(saved, CourseDto.class);
    }
}
