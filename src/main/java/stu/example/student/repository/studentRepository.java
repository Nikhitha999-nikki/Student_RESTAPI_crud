package stu.example.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import stu.example.student.model.Student;

public interface studentRepository extends JpaRepository<Student,Integer> {

}
