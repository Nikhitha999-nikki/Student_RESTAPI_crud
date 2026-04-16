package stu.example.student.service.implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import stu.example.student.exception.StudentNotFoundException;
import stu.example.student.model.Student;
import stu.example.student.repository.studentRepository;
import stu.example.student.service.StudentService;

@Service
public class studentServiceImpl implements StudentService {

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
        if(studentRepository.findById(id).isEmpty()) throw new StudentNotFoundException("student not exist");
        return studentRepository.findById(id).get();
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
