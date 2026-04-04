package stu.example.student.service;

import java.util.List;

import stu.example.student.model.Student;

public interface studentService {
    public String createStudent(Student student);
    public String updateStudent(Student student);
    public Student getStudent(Integer id);
    public List<Student> getAllStudents();
    public String deleteStudent(Integer id);
}
