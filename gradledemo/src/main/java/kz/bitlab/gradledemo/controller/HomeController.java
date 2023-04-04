package kz.bitlab.gradledemo.controller;

import kz.bitlab.gradledemo.dao.ItemDAO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class HomeController {
    @GetMapping(value = "/")
    public String homePage(Model model) {
        model.addAttribute("items", ItemDAO.findAll());
        return "home";
    }
}
