package kz.bitlab.trello.controller;

import kz.bitlab.trello.service.CategoryService;
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
@RequestMapping("/categories")
public class CategoriesController {
    private CategoryService categoryService;
    private FolderService folderService;

    @GetMapping
    public String showAllCategories(Model model) {
        model.addAttribute("page", "categories");
        model.addAttribute("categories", categoryService.getAllCategories());
        return "categories";
    }

    @PostMapping("/add")
    public String addCategoryPost(@RequestParam("category-name") String categoryName) {
        String redirectStr = "/categories?error";

        if (categoryService.addCategory(categoryName) != null) {
            redirectStr = "/categories?success";
        }
        return "redirect:" + redirectStr;
    }
}
