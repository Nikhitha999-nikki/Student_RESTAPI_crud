package stu.example.student.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import stu.example.student.model.Student;
import stu.example.student.service.studentService;

@RestController
@RequestMapping("student")
public class studentAPIController {
   studentService studentService;

    public studentAPIController(studentService studentService) {
        this.studentService = studentService;
    }
   @GetMapping("{id}")
    public Student get(@PathVariable("id")Integer id){
        // db.addStudent(student);
        return studentService.getStudent(id);
    }
    @GetMapping()
    public List<Student> getAll(){
        // db.addStudent(student);
        return studentService.getAllStudents();
    }
    @PostMapping()
    public String create(@RequestBody Student student){
        studentService.createStudent(student);
        return "Student created successfully.";
    }
    @PutMapping()
    public String update(@RequestBody Student student){
        studentService.updateStudent(student);
        return "Student updated successfully.";
    }
    @DeleteMapping("{id}")
    public String delete(@PathVariable("id")Integer id){
        studentService.deleteStudent(id);
        return "Student deleted successfully.";
    }
    
}
