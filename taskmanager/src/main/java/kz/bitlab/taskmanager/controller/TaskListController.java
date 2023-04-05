package kz.bitlab.taskmanager.controller;

import kz.bitlab.taskmanager.dao.TaskDAO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class TaskListController {
    @GetMapping
    public String showTaskList(Model model) {
        model.addAttribute("tasks", TaskDAO.findAll());
        return "task-list";
    }
}
