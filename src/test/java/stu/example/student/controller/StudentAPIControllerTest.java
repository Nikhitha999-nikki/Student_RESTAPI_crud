package stu.example.student.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import ch.qos.logback.core.net.ObjectWriter;
import stu.example.student.controller.StudentAPIController;
import stu.example.student.model.Student;
import stu.example.student.service.StudentService;

@WebMvcTest(StudentAPIController.class)
public class StudentAPIControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private StudentService studentService;
    
    Student s1;
    Student s2;
    List<Student> studentList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        s1 = new Student("xx", 1, "yyy", 1234567);
        s2 = new Student("aa", 2, "bbb", 7654321);
        studentList.add(s1);
        studentList.add(s2);
    }

    @Test
    void testCreate() throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        com.fasterxml.jackson.databind.ObjectWriter objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
        String requestJson = objectWriter.writeValueAsString(s1);

       when(studentService.createStudent(s1)).thenReturn("successs");
        this.mockMvc.perform(post("/student")
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestJson))
            .andDo(print())
            .andExpect(status().isOk());
    }

    @Test
    void testDelete() throws Exception {
        when(studentService.deleteStudent(1)).thenReturn("Student deleted successfully.");
        this.mockMvc.perform(delete("/student/1"))
            .andDo(print())
            .andExpect(status().isOk());
    }

    @Test
    void testGet() throws Exception {
        when(studentService.getStudent(1)).thenReturn(s1);
        this.mockMvc.perform(get("/student/1"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.message").value("your requested student details here.."))
            .andExpect(jsonPath("$.data.id").value(1))
            .andExpect(jsonPath("$.data.name").value("xx"))
            .andExpect(jsonPath("$.data.address").value("yyy"))
            .andExpect(jsonPath("$.data.contact").value(1234567));
    }

    @Test
    void testGetAll() throws Exception {
        when(studentService.getAllStudents()).thenReturn(studentList);
        this.mockMvc.perform(get("/student"))
            .andDo(print())
            .andExpect(status().isOk());
        //     .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        //     .andExpect(jsonPath("$.message").value("your requested student details here.."))
        //     .andExpect(jsonPath("$.data[*].id").value(1))
        //     .andExpect(jsonPath("$.data[*].name").value("xx"))
        //     .andExpect(jsonPath("$.data[*].address").value("yyy"))
        //     .andExpect(jsonPath("$.data[*].contact").value(1234567));
    }

    @Test
    void testUpdate() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        com.fasterxml.jackson.databind.ObjectWriter objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
        String requestJson = objectWriter.writeValueAsString(s1);

       when(studentService.updateStudent(s1)).thenReturn("successs");
        this.mockMvc.perform(put("/student")
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestJson))
            .andDo(print())
            .andExpect(status().isOk());
    }
}
