package kz.bitlab.mycrm.controller;

import kz.bitlab.mycrm.entities.ApplicationRequest;
import kz.bitlab.mycrm.entities.Course;
import kz.bitlab.mycrm.entities.Operator;
import kz.bitlab.mycrm.services.ApplicationRequestService;
import kz.bitlab.mycrm.services.CourseService;
import kz.bitlab.mycrm.services.OperatorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor
@RequestMapping("/manage")
public class ApplicationRequestManagementController {
    private ApplicationRequestService applicationRequestService;
    private CourseService courseService;
    private OperatorService operatorService;


    @GetMapping("/add-ar")
    public String addApplicationRequest(Model model) {
        model.addAttribute("page", "add-ar");
        model.addAttribute("courses", courseService.getAllCourses());
        return "/manage/add-application-request";
    }

    @PostMapping("/add-ar")
    public String addApplicationRequestPost(
            @RequestParam(name = "ar-user-name") String userName,
            @RequestParam(name = "ar-course-name") String courseName,
            @RequestParam(name = "ar-phone") String phone,
            @RequestParam(name = "ar-comment") String comment) {
        String redirectStr = "/manage/add-ar?error";

        Course course = courseService.getCourseByName(courseName);

        ApplicationRequest ar = new ApplicationRequest(
                null, userName, comment, phone, false, course, null);

        if (applicationRequestService.createApplicationRequest(ar) != null) {
            redirectStr = "/?success";
        }

        return "redirect:" + redirectStr;
    }

    @GetMapping("/edit-ar/{id}")
    public String editApplicationRequest(
            @PathVariable long id,
            Model model) {
        ApplicationRequest ar = applicationRequestService.getApplicationRequest(id);
        if (ar != null) {
            model.addAttribute("allCourses", courseService.getAllCourses());
            model.addAttribute("allOperators", operatorService.getAllOperators());
            model.addAttribute("ar", ar);
            return "/manage/edit-application-request";
        }

        return "/home";
    }

    @PostMapping("/handle-ar/{id}")
    public String handleApplicationRequestPost(
            @PathVariable long id,
            @RequestParam("operators") List<Long> operatorIds) {

        String redirectStr = String.format("/manage/edit-ar/%d?error", id);

        ApplicationRequest ar = applicationRequestService.getApplicationRequest(id);
        if (ar != null && operatorIds != null) {
            ar.setHandled(true);
            ar.setOperators(operatorService.getAllOperatorsByIds(operatorIds));

            if (applicationRequestService.updateApplicationRequest(ar) != null) {
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

    @PostMapping("/ar-remove-operator/{id}")
    public String removeOperatorPost(
            @PathVariable long id,
            @RequestParam("operator-id") Long operatorId) {

        String redirectStr = String.format("/manage/edit-ar/%d?error", id);

        ApplicationRequest ar = applicationRequestService.getApplicationRequest(id);
        Operator op = operatorService.getOperatorById(operatorId);
        if (ar != null && op != null) {
            ar.getOperators().remove(op);
            if (applicationRequestService.updateApplicationRequest(ar) != null) {
                redirectStr = String.format("/manage/edit-ar/%d?success", id);
            }
        } else {
            redirectStr = "/?error=id_not_found";
        }

        return "redirect:" + redirectStr;
    }
}
