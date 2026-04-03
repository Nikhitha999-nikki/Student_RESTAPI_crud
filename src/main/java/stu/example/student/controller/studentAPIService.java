package stu.example.student.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import stu.example.student.model.Student;

@RestController
@RequestMapping("student")
public class studentAPIService {
   private Student student;
   @GetMapping("{id}")
    public Student get(Integer id) {
        // db.addStudent(student);
        return student;
    }
    @PostMapping()
    public String create(@RequestBody Student student){
        this.student=student;
        return "Student created successfully.";
    }
    @PutMapping()
    public String update(@RequestBody Student student){
        this.student=student;
        return "Student updated successfully.";
    }
    @DeleteMapping("{id}")
    public String delete(Integer id)
    {
        this.student=null;
        return "Student deleted successfully.";
    }
    
}
