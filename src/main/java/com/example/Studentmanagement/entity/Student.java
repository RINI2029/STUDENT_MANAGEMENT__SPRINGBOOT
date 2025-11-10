package com.example.Studentmanagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity                         // Marks this class as a JPA entity (table)
@Table(name = "students")       // Optional: specifies table name
@Data                           // Lombok: generates getters, setters, toString()
@NoArgsConstructor              // Lombok: default constructor
@AllArgsConstructor             // Lombok: all-args constructor
public class Student {

    @Id                                             // Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true)
    private String email;

    private String course;
}
