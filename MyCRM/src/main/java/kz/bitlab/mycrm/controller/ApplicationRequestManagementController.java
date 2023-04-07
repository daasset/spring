package kz.bitlab.mycrm.controller;

import kz.bitlab.mycrm.entities.ApplicationRequest;
import kz.bitlab.mycrm.repository.ApplicationRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        String redirectStr = "/manage/add-ar?error";

        ApplicationRequest ar = new ApplicationRequest(
                null, userName, courseName, comment, phone, false);

        if (applicationRequestRepository.save(ar) != null) {
            redirectStr = "/?success";
        }

        return "redirect:" + redirectStr;
    }

    @GetMapping("/edit-ar/{id}")
    public String editApplicationRequest(
            @PathVariable long id,
            Model model) {
        ApplicationRequest ar = applicationRequestRepository.findById(id).get();
        if (ar != null) {
            model.addAttribute("ar", ar);
            return "/manage/edit-application-request";
        }

        return "/home";
    }

    @PostMapping("/handle-ar/{id}")
    public String handleApplicationRequestPost(
            @PathVariable long id) {
        String redirectStr = String.format("/manage/edit-ar/%d?error", id);

        ApplicationRequest ar = applicationRequestRepository.findById(id).get();
        if (ar != null) {
            ar.setHandled(true);
            if (applicationRequestRepository.save(ar) != null) {
                redirectStr = String.format("/manage/edit-ar/%d?success", id);
            }
        } else {
            redirectStr = "/?error=id_not_found";
        }

        return "redirect:" + redirectStr;
    }

    @PostMapping("/delete-ar/{id}")
    public String deleteApplicationRequestPost(
            @PathVariable long id) {
        String redirectStr = String.format("/manage/edit-ar/%d?error", id);

        ApplicationRequest ar = applicationRequestRepository.findById(id).get();
        if (ar != null) {
            ar.setHandled(true);
            applicationRequestRepository.delete(ar);
            redirectStr = "/?success";
        } else {
            redirectStr = "/?error=id_not_found";
        }

        return "redirect:" + redirectStr;
    }
}
