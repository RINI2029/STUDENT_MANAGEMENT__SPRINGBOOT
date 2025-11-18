package com.example.studentmanagement.dto;

import lombok.Data;
import java.util.List;

@Data
public class StudentDto {
    private Long id;
    private String name;
    private String email;
    private String course;
    private List<CourseDto> courses;
}
