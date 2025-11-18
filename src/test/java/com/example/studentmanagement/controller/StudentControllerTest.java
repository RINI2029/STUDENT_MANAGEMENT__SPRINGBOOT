package com.example.studentmanagement.controller;

import com.example.studentmanagement.entity.Student;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs(outputDir = "target/snippets")
@ExtendWith(RestDocumentationExtension.class)
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    // ✅ CREATE
    @Test
    void addStudent_shouldGenerateDocs() throws Exception {
        Student student = new Student();
        student.setName("Rini");
        student.setEmail("rini@example.com");
        student.setCourse("Spring Boot");

        mockMvc.perform(post("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(student)))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("students-create"));
    }

    // ✅ READ - ALL
    @Test
    void getAllStudents_shouldGenerateDocs() throws Exception {
        mockMvc.perform(get("/students"))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("students-list"));
    }

    // ✅ READ - BY ID
    @Test
    void getStudentById_shouldGenerateDocs() throws Exception {
        // First create a student to ensure an ID exists
        Student student = new Student();
        student.setName("Rini");
        student.setEmail("rini@example.com");
        student.setCourse("Spring Boot");

        mockMvc.perform(post("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(student)))
                .andExpect(status().isOk());

        // Then retrieve it
        mockMvc.perform(get("/students/{id}", 1L))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("students-get"));
    }

    // ✅ UPDATE
    @Test
    void updateStudent_shouldGenerateDocs() throws Exception {
        // Step 1: Create a student first
        Student student = new Student();
        student.setName("Rini");
        student.setEmail("rini@example.com");
        student.setCourse("Spring Boot");

        String createdJson = mockMvc.perform(post("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(student)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        // Step 2: Extract the created student's ID from the response
        Student created = objectMapper.readValue(createdJson, Student.class);

        // Step 3: Prepare updated data
        Student updated = new Student();
        updated.setName("Rini Rayan");
        updated.setEmail("rini.rayan@example.com");
        updated.setCourse("Spring Advanced");

        // Step 4: Update the existing student using its real ID
        mockMvc.perform(put("/students/{id}", created.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updated)))
                .andExpect(status().isOk())  // ✅ Now the ID exists
                .andDo(print())
                .andDo(document("students-update"));
    }


    // ✅ DELETE
    @Test
    void deleteStudent_shouldGenerateDocs() throws Exception {
        // Create a student first
        Student student = new Student();
        student.setName("Rini");
        student.setEmail("rini@example.com");
        student.setCourse("Spring Boot");

        mockMvc.perform(post("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(student)))
                .andExpect(status().isOk());

        // Now delete the same student
        mockMvc.perform(delete("/students/{id}", 1L))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("students-delete"));
    }
}
