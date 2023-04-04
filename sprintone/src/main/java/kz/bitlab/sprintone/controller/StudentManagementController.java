package kz.bitlab.sprintone.controller;

import kz.bitlab.sprintone.dao.StudentDAO;
import kz.bitlab.sprintone.model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
}
