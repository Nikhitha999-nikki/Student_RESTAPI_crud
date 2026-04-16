package stu.example.student.repository;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import stu.example.student.model.Student;

@DataJpaTest
public class StudentRepositoryTest {

    @Autowired
    private studentRepository studentRepository;
    Student student;

    @BeforeEach
    void setUp()
    {
        student=new Student("xx",1,"yyy",1234567);
        studentRepository.save(student);
    }

    @AfterEach
    void tearDown()
    {
        student=null;
        studentRepository.deleteAll();
    }

    @Test //testcase
    void testFindByStudent_Found() {
        List<Student> studentList = studentRepository.findByName("xx");
        assertThat(studentList.get(0).getId()).isEqualTo(student.getId());  
        assertThat(studentList.get(0).getAddress()).isEqualTo(student.getAddress());
    }
    

    @Test //teastcase
    void testFindByStudent_NotFound()
    {
        List<Student> studentList=studentRepository.findByName("gcp");
        assertThat(studentList.isEmpty()).isTrue();
        // assertThat(studentList.get(0).getAddress()).isEqualTo(student.getAddress());
    }



}
