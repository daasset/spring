package kz.bitlab.trello.controller;

import kz.bitlab.trello.entity.Folder;
import kz.bitlab.trello.service.FolderService;
import kz.bitlab.trello.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
@RequestMapping("/")
public class HomeController {
    private FolderService folderService;
    private TaskService taskService;

    @GetMapping
    public String showHome(Model model) {
        for(var entry : taskService.getTasksCountByFolderMap().entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
        model.addAttribute("taskCountByFolderMap", taskService.getTasksCountByFolderMap());
        model.addAttribute("folders", folderService.getAllFolders());
        return "home";
    }

    @PostMapping("/folder/add")
    private String addFolderPost(
            @RequestParam("folder-name") String folderName) {
        String redirectStr = "/?error";
        Folder folder = Folder.builder().name(folderName).build();
        if (folderService.addFolder(folder) != null) {
            redirectStr = "/?success";
        }
        return "redirect:" + redirectStr;
    }
}
