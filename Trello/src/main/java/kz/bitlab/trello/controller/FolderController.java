package kz.bitlab.trello.controller;

import kz.bitlab.trello.entity.Category;
import kz.bitlab.trello.entity.Folder;
import kz.bitlab.trello.entity.Task;
import kz.bitlab.trello.service.CategoryService;
import kz.bitlab.trello.service.FolderService;
import kz.bitlab.trello.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/folder")
public class FolderController {
    private FolderService folderService;
    private TaskService taskService;
    private CategoryService categoryService;

    @GetMapping("/{id}")
    public String showAllTasks(
            @PathVariable long id,
            Model model) {
        Folder folder = folderService.getFolderById(id);
        List<Category> unassignedCategories = categoryService.getAllCategories();
        unassignedCategories.removeAll(folder.getCategories());

        model.addAttribute("folder", folder);
        model.addAttribute("tasks", taskService.getTasksByFolder(folder));
        model.addAttribute("unassignedCategories", unassignedCategories);
        model.addAttribute("allTaskStatuses", Task.Status.values());

        return "folder";
    }

    @PostMapping("/task/add")
    private String addTaskPost(
            @RequestParam("folder-id") long folderId,
            @RequestParam("task-title") String taskTitle,
            @RequestParam("task-description") String taskDescription) {
        String redirectStr = "/folder/" + folderId + "?error";

        Folder folder = folderService.getFolderById(folderId);
        Task task = Task.builder()
                .title(taskTitle)
                .description(taskDescription)
                .folder(folder)
                .build();
        if (taskService.addTask(task) != null) {
            redirectStr = "/folder/" + folderId + "?success";
        }

        return "redirect:" + redirectStr;
    }

    @PostMapping("/task/edit")
    private String editTaskPost(
            @RequestParam("folder-id") long folderId,
            @RequestParam("task-id") long taskId,
            @RequestParam("task-title") String taskTitle,
            @RequestParam("task-description") String taskDescription,
            @RequestParam("task-status") Task.Status status) {
        String redirectStr = "/folder/" + folderId + "?error";

        Task task = taskService.getTaskById(taskId);
        if (task != null) {
            task.setTitle(taskTitle);
            task.setDescription(taskDescription);
            task.setStatus(status);
        }
        if (taskService.editTask(task) != null) {
            redirectStr = "/folder/" + folderId + "?success";
        }

        return "redirect:" + redirectStr;
    }

    @PostMapping("/category/add")
    private String assignCategoryPost(
            @RequestParam("folder-id") long folderId,
            @RequestParam("category-id") long categoryId) {
        String redirectStr = "/folder/" + folderId + "?error";

        Folder folder = folderService.getFolderById(folderId);
        Category category = categoryService.getCategoryById(categoryId);
        if (!folder.getCategories().contains(category)) {
            folder.getCategories().add(category);
        }

        if (folderService.editFolder(folder) != null) {
            redirectStr = "/folder/" + folderId + "?success";
        }

        return "redirect:" + redirectStr;
    }

    @PostMapping("/category/remove")
    private String removeCategoryPost(
            @RequestParam("folder-id") long folderId,
            @RequestParam("category-id") long categoryId) {
        String redirectStr = "/folder/" + folderId + "?error";

        Folder folder = folderService.getFolderById(folderId);
        Category category = categoryService.getCategoryById(categoryId);

        folder.getCategories().remove(category);
        if (folderService.editFolder(folder) != null) {
            redirectStr = "/folder/" + folderId + "?success";
        }

        return "redirect:" + redirectStr;
    }
}
