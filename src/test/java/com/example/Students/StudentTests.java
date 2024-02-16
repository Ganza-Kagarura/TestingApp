package com.example.Students;

import com.example.Students.Student.StudentRepository;
import com.example.Students.Student.Student;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StudentTests {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    @Order(1)
    @Rollback(value = false)
    public void saveStudentTest() {
        Student student = Student.builder()
                .firstName("Ganza")
                .lastName("Isaac")
                .email("ganza22@gmail.com")
                .build();
        studentRepository.save(student);

        Assertions.assertThat(student.getId()).isGreaterThan(0);
    }

    @Test
    @Order(2)
    public void getStudentTest() {
        Student student = studentRepository.findById(1L).get();

        Assertions.assertThat(student.getId()).isEqualTo(1L);
    }

    @Test
    @Order(3)
    public void getListofStudentTest() {
        List<Student> student = studentRepository.findAll();

        Assertions.assertThat(student.size()).isGreaterThan(0);
    }

    @Test
    @Order(4)
    @Rollback(value = false)
    public void updateStudentTest() {
        Student student = studentRepository.findById(1L).get();

        student.setEmail("ram@gmail.com");

        Student employeeUpdated = studentRepository.save(student);

        Assertions.assertThat(employeeUpdated.getEmail()).isEqualTo("ram@gmail.com");
    }

    @Test
    @Order(5)
    @Rollback(value = false)
    public void deleteStudentTest() {
        Student student = studentRepository.findById(1L).get();

        studentRepository.delete(student);

        Student student1 = null;

        Optional<Student> optionalEmployee = studentRepository.findByEmail("ram@gmail.com");

        if(optionalEmployee.isPresent()) {
            student1 = optionalEmployee.get();
        }

        Assertions.assertThat(student1).isNull();
    }
}
