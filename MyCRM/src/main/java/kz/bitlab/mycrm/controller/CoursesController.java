package kz.bitlab.mycrm.controller;

import kz.bitlab.mycrm.entities.Course;
import kz.bitlab.mycrm.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/courses")
public class CoursesController {

    private CourseRepository courseRepository;

    public CoursesController(@Autowired CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @GetMapping
    public String showCourses(Model model) {
        model.addAttribute("page", "courses");
        model.addAttribute("courses", courseRepository.findAll());
        return "courses";
    }

    @GetMapping("/add")
    public String addCourse(Model model) {
        model.addAttribute("page", "add-course");
        return "/manage/add-course";
    }

    @PostMapping("/add")
    public String addCourse(
            @RequestParam(name = "course-name") String name,
            @RequestParam(name = "course-description") String description,
            @RequestParam(name = "course-price") int price) {
        Course course = new Course(null, name, description, price);
        String redirectStr = "/courses/add?error";
        if (courseRepository.save(course) != null) {
            redirectStr = "/courses?success";
        }

        return "redirect:" + redirectStr;
    }
}
