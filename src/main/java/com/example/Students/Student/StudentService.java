package com.example.Students.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired private StudentRepository repo;

    public List<Student> listAll() {
        return (List<Student>) repo.findAll();
    }

    public void save(Student student) {
        repo.save(student);
    }

    public Student get(Integer id) throws StudentNotFoundException {
        Optional<Student> result = repo.findById(Long.valueOf(id));
        if (result.isPresent()) {
            return result.get();
        }
        throw new StudentNotFoundException("Could not find any users with ID " + id);
    }

    public void delete(Integer id)  {
        Long count = repo.countById(id);
        if (count == null || count == 0) {
//            throw new UserNotFoundException("Could not find any users with ID " + id);
        }
        repo.deleteById(Long.valueOf(id));
    }
}
