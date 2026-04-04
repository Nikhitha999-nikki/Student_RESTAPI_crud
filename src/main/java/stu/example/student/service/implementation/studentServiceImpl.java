package stu.example.student.service.implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import stu.example.student.model.Student;
import stu.example.student.repository.studentRepository;
import stu.example.student.service.studentService;

@Service
public class studentServiceImpl implements studentService {

    studentRepository studentRepository;


    public studentServiceImpl(studentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public String createStudent(Student student) {
        studentRepository.save(student);
        return "Student created successfully";
    }

    @Override
    public String updateStudent(Student student) {
        studentRepository.save(student);
        return "Student updated successfully";
    }

    @Override
    public Student getStudent(Integer id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public String deleteStudent(Integer id) {
        studentRepository.deleteById(id);
        return "deleted successfully";
    }

}
