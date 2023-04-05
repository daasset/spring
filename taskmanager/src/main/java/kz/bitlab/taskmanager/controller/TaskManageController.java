package kz.bitlab.taskmanager.controller;

import kz.bitlab.taskmanager.dao.TaskDAO;
import kz.bitlab.taskmanager.model.Task;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
@RequestMapping("/manage")
public class TaskManageController {
    @PostMapping("/add-task")
    public String addTaskPost(
            @RequestParam(name = "task-name") String name,
            @RequestParam(name = "task-description") String description,
            @RequestParam(name = "task-duedate") LocalDate dueDate) {
        String redirectStr = "/?error";

        Task task = new Task(null, name, description, dueDate, false);
        if (TaskDAO.create(task)) {
            redirectStr = "/?success";
        }

        return "redirect:" + redirectStr;
    }

    @PostMapping("/edit-task")
    public String editTaskPost(
            @RequestParam(name = "task-id") long id,
            @RequestParam(name = "task-name") String name,
            @RequestParam(name = "task-description") String description,
            @RequestParam(name = "task-duedate") LocalDate dueDate,
            @RequestParam(name = "task-complete") boolean complete) {
        String redirectStr = "/?error";

        Task task = new Task(id, name, description, dueDate, complete);
        if (TaskDAO.edit(task)) {
            redirectStr = "/?success";
        }

        return "redirect:" + redirectStr;
    }

    @PostMapping("/delete-task")
    public String editTaskPost(
            @RequestParam(name = "task-id") long id) {
        String redirectStr = "/?error";

        Task task = TaskDAO.findById(id);
        if (TaskDAO.delete(task)) {
            redirectStr = "/?success";
        }

        return "redirect:" + redirectStr;
    }
}
