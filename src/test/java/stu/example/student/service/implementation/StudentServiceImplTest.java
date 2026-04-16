package stu.example.student.service.implementation;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import stu.example.student.model.Student;
import stu.example.student.repository.studentRepository;
import stu.example.student.service.StudentService;
import stu.example.student.exception.StudentNotFoundException;

@ExtendWith(MockitoExtension.class)
class StudentServiceImplTest {

    @Mock
    private studentRepository studentRepository;

    private StudentService studentService;
    private Student student;

    @BeforeEach
    void setUp() {
        studentService = new studentServiceImpl(studentRepository);
        student = new Student("xx", 1, "yyy", 1234567);
    }

    @AfterEach
    void tearDown() {
        reset(studentRepository);
    }

    @Test
    void testCreateStudent() {
        when(studentRepository.save(student)).thenReturn(student);
        String result = studentService.createStudent(student);
        assertThat(result).isEqualTo("Student created successfully");
        verify(studentRepository, times(1)).save(student);
    }

    @Test
    void testUpdateStudent() {
        when(studentRepository.save(student)).thenReturn(student);
        String result = studentService.updateStudent(student);
        assertThat(result).isEqualTo("Student updated successfully");
        verify(studentRepository, times(1)).save(student);
    }

    @Test
    void testGetStudent() {
        int studentId = 2;
        when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));
        Student result = studentService.getStudent(studentId);
        assertThat(result.getName()).isEqualTo("xx");
        verify(studentRepository, atLeastOnce()).findById(studentId);
    }  

    @Test
    void testGetStudentNotFound() {
        int invalidId = 2;
        when(studentRepository.findById(invalidId)).thenReturn(Optional.empty());
        assertThrows(StudentNotFoundException.class, () -> studentService.getStudent(invalidId));
    }

    @Test
    void testGetAllStudents() {
        List<Student> studentList = List.of(student);
        when(studentRepository.findAll()).thenReturn(studentList);
        List<Student> result = studentService.getAllStudents();
        assertThat(result).hasSize(1);
        verify(studentRepository, times(1)).findAll();
    }

    @Test
    void testDeleteStudent() {
        int studentId = 2;
        doAnswer(Answers.CALLS_REAL_METHODS).when(studentRepository).deleteById(studentId);
        String result = studentService.deleteStudent(studentId);
        assertThat(result).isEqualTo("deleted successfully");
        verify(studentRepository, times(1)).deleteById(studentId);
    }
}  