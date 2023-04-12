package kz.bitlab.mycrm.controller;

import kz.bitlab.mycrm.repository.ApplicationRequestRepository;
import kz.bitlab.mycrm.services.ApplicationRequestService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
@AllArgsConstructor
public class HomeController {
    private ApplicationRequestService applicationRequestService;

    @GetMapping
    public String showAllApplicationRequests(Model model) {
        model.addAttribute("page", "");
        model.addAttribute("applicationRequests", applicationRequestService.getAllApplicationRequests());
        return "home";
    }

    @PostMapping
    public String searchApplicationRequests(
            @RequestParam("search-string") String searchString,
            Model model) {
        model.addAttribute("page", "");
        model.addAttribute("applicationRequests",
                applicationRequestService.getAllApplicationRequestsByUserNameOrCommentOrCourseNameFragment(searchString));
        return "home";
    }

    @GetMapping("/list/new")
    public String showNewApplicationRequests(Model model) {
        model.addAttribute("page", "new");
        model.addAttribute("applicationRequests", applicationRequestService.getAllApplicationRequestsByHandled(false));
        return "home";
    }

    @GetMapping("/list/handled")
    public String showHandledApplicationRequests(Model model) {
        model.addAttribute("page", "handled");
        model.addAttribute("applicationRequests", applicationRequestService.getAllApplicationRequestsByHandled(true));
        return "home";
    }
}
