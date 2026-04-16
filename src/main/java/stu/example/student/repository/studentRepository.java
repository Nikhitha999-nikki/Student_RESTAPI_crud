package stu.example.student.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import stu.example.student.model.Student;


public interface studentRepository extends JpaRepository<Student,Integer> {
    public List<Student> findByName(String name);
}
