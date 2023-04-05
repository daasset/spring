package kz.bitlab.mycrm.controller;

import kz.bitlab.mycrm.repository.ApplicationRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    private ApplicationRequestRepository applicationRequestRepository;

    @GetMapping
    public String showAllApplicationRequests(Model model) {
        model.addAttribute("page", "");
        model.addAttribute("applicationRequests", applicationRequestRepository.findAll());
        return "home";
    }

    @GetMapping("/list/new")
    public String showNewApplicationRequests(Model model) {
        model.addAttribute("page", "new");
        model.addAttribute("applicationRequests", applicationRequestRepository.findByHandled(false));
        return "home";
    }

    @GetMapping("/list/handled")
    public String showHandledApplicationRequests(Model model) {
        model.addAttribute("page", "handled");
        model.addAttribute("applicationRequests", applicationRequestRepository.findByHandled(true));
        return "home";
    }
}
