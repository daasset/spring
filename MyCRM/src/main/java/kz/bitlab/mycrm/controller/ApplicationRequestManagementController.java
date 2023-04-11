package kz.bitlab.mycrm.controller;

import kz.bitlab.mycrm.entities.ApplicationRequest;
import kz.bitlab.mycrm.entities.Course;
import kz.bitlab.mycrm.services.ApplicationRequestService;
import kz.bitlab.mycrm.services.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping("/manage")
public class ApplicationRequestManagementController {
    private ApplicationRequestService applicationRequestService;
    private CourseService courseService;


    @GetMapping("/add-ar")
    public String addApplicationRequest(Model model) {
        model.addAttribute("page", "add-ar");
        model.addAttribute("courses", courseService.getAllCourses());
        return "/manage/add-application-request";
    }

    @PostMapping("/add-ar")
    public String addApplicationRequestPost(@RequestParam(name = "ar-user-name") String userName, @RequestParam(name = "ar-course-name") String courseName, @RequestParam(name = "ar-phone") String phone, @RequestParam(name = "ar-comment") String comment) {
        String redirectStr = "/manage/add-ar?error";

        Course course = courseService.getCourseByName(courseName);

        ApplicationRequest ar = new ApplicationRequest(null, userName, comment, phone, false, course);

        if (applicationRequestService.createApplicationRequest(ar) != null) {
            redirectStr = "/?success";
        }

        return "redirect:" + redirectStr;
    }

    @GetMapping("/edit-ar/{id}")
    public String editApplicationRequest(@PathVariable long id, Model model) {
        ApplicationRequest ar = applicationRequestService.getApplicationRequest(id);
        if (ar != null) {
            model.addAttribute("courses", courseService.getAllCourses());
            model.addAttribute("ar", ar);
            return "/manage/edit-application-request";
        }

        return "/home";
    }

    @PostMapping("/handle-ar/{id}")
    public String handleApplicationRequestPost(@PathVariable long id) {
        String redirectStr = String.format("/manage/edit-ar/%d?error", id);

        ApplicationRequest ar = applicationRequestService.getApplicationRequest(id);
        if (ar != null) {
            ar.setHandled(true);
            if (applicationRequestService.updateApplicationRequest(ar) != null) {
                redirectStr = String.format("/manage/edit-ar/%d?success", id);
            }
        } else {
            redirectStr = "/?error=id_not_found";
        }

        return "redirect:" + redirectStr;
    }

    @PostMapping("/delete-ar/{id}")
    public String deleteApplicationRequestPost(@PathVariable long id) {
        String redirectStr = String.format("/manage/edit-ar/%d?error", id);

        ApplicationRequest ar = applicationRequestService.getApplicationRequest(id);
        if (ar != null) {
            ar.setHandled(true);
            applicationRequestService.deleteApplicationRequest(ar);
            redirectStr = "/?success";
        } else {
            redirectStr = "/?error=id_not_found";
        }

        return "redirect:" + redirectStr;
    }
}
