package kz.bitlab.mycrm.controller;

import kz.bitlab.mycrm.entities.ApplicationRequest;
import kz.bitlab.mycrm.repository.ApplicationRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/manage")
public class ApplicationRequestManagementController {
    @Autowired
    ApplicationRequestRepository applicationRequestRepository;

    @GetMapping("/add-ar")
    public String addApplicationRequest() {
        return "/manage/add-application-request";
    }

    @PostMapping("/add-ar")
    public String addApplicationRequestPost(
            @RequestParam(name = "ar-user-name") String userName,
            @RequestParam(name = "ar-course-name") String courseName,
            @RequestParam(name = "ar-phone") String phone,
            @RequestParam(name = "ar-comment") String comment) {
        ApplicationRequest ar = new ApplicationRequest(
                null, userName, courseName, comment, phone, false);
        applicationRequestRepository.save(ar);

        return "redirect:/";
    }
}
