package kz.bitlab.mycrm.services;

import kz.bitlab.mycrm.entities.Course;

import java.util.List;

public interface CourseService {

    /*
        Getters
     */
    Course getCourseByName(String name);
    List<Course> getAllCourses();

    /*
        Modifiers
     */
    Course createCourse(Course course);

}
