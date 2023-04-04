package kz.bitlab.sprintone.controller;

import kz.bitlab.sprintone.dao.StudentDAO;
import kz.bitlab.sprintone.model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/manage")
public class StudentManagementController {
    @GetMapping(value = "/add-student")
    public String addStudent() {
        return "manage/add-student";
    }

    @PostMapping(value = "/add-student")
    public String addStudentPost(
            @RequestParam(name = "student-name") String name,
            @RequestParam(name = "student-surname") String surname,
            @RequestParam(name = "student-score") int score) {
        String redirectStr = "/manage/add-student?error";

        Student student = new Student(null, name, surname, score);
        if (StudentDAO.create(student)) {
            redirectStr = "/";
        }

        return "redirect:" + redirectStr;
    }

    @GetMapping(value = "/edit-student/{id}")
    public String editStudent(
            @PathVariable long id,
            Model model) {
        model.addAttribute("student", StudentDAO.findById(id));
        return "manage/edit-student";
    }

    @PostMapping(value = "/edit-student/{id}")
    public String editStudentPost(
            @PathVariable long id,
            @RequestParam(name = "student-name") String name,
            @RequestParam(name = "student-surname") String surname,
            @RequestParam(name = "student-score") int score) {
        String redirectStr = "/manage/edit-student/" + id + "?error";

        Student student = new Student(id, name, surname, score);
        if (StudentDAO.edit(student)) {
            redirectStr = "/manage/edit-student/" + id + "?success";
        }

        return "redirect:" + redirectStr;
    }

    @PostMapping(value = "/remove-student/{id}")
    public String removeStudentPost(
            @PathVariable long id) {
        String redirectStr = "/manage/edit-student/" + id + "?error";

        Student student = StudentDAO.findById(id);
        if (StudentDAO.delete(student)) {
            redirectStr = "/";
        }

        return "redirect:" + redirectStr;
    }
}
