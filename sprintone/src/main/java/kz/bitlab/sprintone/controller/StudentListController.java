package kz.bitlab.sprintone.controller;

import kz.bitlab.sprintone.dao.StudentDAO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/")
public class StudentListController {
    @GetMapping
    public String showStudents(Model model) {
        model.addAttribute("students", StudentDAO.findAll());
        return "/list/list-students";
    }
}
