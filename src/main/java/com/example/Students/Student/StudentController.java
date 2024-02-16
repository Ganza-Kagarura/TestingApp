package com.example.Students.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class StudentController {
    @Autowired private com.example.Students.Student.StudentService service;

    @GetMapping("/student")
    public String showUserList(Model model) {
        List<Student> listUsers = service.listAll();
        model.addAttribute("listStudents", listUsers);

        return "student";
    }

    @GetMapping("/student/new")
    public String showNewForm(Model model) {
        model.addAttribute("student", new Student());
        model.addAttribute("pageTitle", "Add New Student");
        return "student_form";
    }

    @PostMapping("/student/save")
    public String saveUser(Student user) {
        service.save(user);
        return "redirect:/users";
    }

    @GetMapping("/users/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model) {

        Student student = null;
        try {
            student = service.get(id);
        } catch (StudentNotFoundException e) {
            throw new RuntimeException(e);
        }
        model.addAttribute("student", student);
            model.addAttribute("pageTitle", "Edit Student (ID: " + id + ")");

            return "user_form";
//            } catch (Error e) {
//            return "redirect:/student";
//         catch (StudentNotFoundException e) {
//            throw new RuntimeException(e);
//        }
    }

    @GetMapping("/student/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id) {
        try {
            service.delete(id);
//            ra.addFlashAttribute("message", "The user ID " + id + " has been deleted.");
        } catch (Error e) {
//            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/users";
    }
}
