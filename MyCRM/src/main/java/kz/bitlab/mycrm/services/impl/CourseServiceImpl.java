package kz.bitlab.mycrm.services.impl;

import kz.bitlab.mycrm.entities.Course;
import kz.bitlab.mycrm.repository.CourseRepository;
import kz.bitlab.mycrm.services.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CourseServiceImpl implements CourseService {

    private CourseRepository courseRepository;
    @Override
    public Course getCourseByName(String name) {
        return courseRepository.findByName(name).get();
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course createCourse(Course course) {
        if (course.getId() != null) {
            throw new IllegalArgumentException("Cannot create Course with existing Id");
        }
        return courseRepository.save(course);
    }
}
