package kz.bitlab.trello.controller;

import kz.bitlab.trello.entity.Folder;
import kz.bitlab.trello.service.FolderService;
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

    @GetMapping
    public String showHome(Model model) {
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
