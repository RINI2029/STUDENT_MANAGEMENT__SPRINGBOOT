package com.example.Studentmanagement.repository;

import com.example.Studentmanagement.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    // 🔍 Custom query method example
    Student findByEmail(String email);
}
