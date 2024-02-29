package org.telusko.course.TeluskoApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.telusko.course.TeluskoApp.dao.CourseRepo;
import org.telusko.course.TeluskoApp.exceptions.CourseNotFoundException;
import org.telusko.course.TeluskoApp.model.Course;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    @Autowired
    private CourseRepo repo;

    public List<Course> getAllCourses() {
        List<Course> courses = repo.findAll();

        if(courses.isEmpty()){
            throw new CourseNotFoundException("Course not found");
        } else {
            return courses;
        }
    }

    public String addCourse(Course course) {
        repo.save(course);
        return "Course added successfully";
    }

    public Course getCourseById(Integer id) {
        Optional<Course> courseOptional = repo.findById(id);
        return courseOptional.orElseThrow(() -> new CourseNotFoundException("Course not found"));
    }

    public Course getByCourseName(String courseName) {
        Optional<Course> courseOptional = repo.findByCourseName(courseName);
        return courseOptional.orElseThrow(() -> new CourseNotFoundException("Course not found"));
    }

    public String updateCourse(Course course) {
        Optional<Course> courseOptional = repo.findById(course.getId());
        courseOptional.ifPresentOrElse((existingCourse -> {
            existingCourse.setCourseName(course.getCourseName());
            existingCourse.setCourseDescription(course.getCourseDescription());
            existingCourse.setCourseDuration(course.getCourseDuration());
            existingCourse.setCourseFees(course.getCourseFees());

            repo.save(existingCourse);
        }), () -> {throw new CourseNotFoundException("Course not found");});

        return "Course updated successfully";
    }

    public String deleteById(Integer id) {
        Optional<Course> courseOptional = repo.findById(id);
        courseOptional.ifPresentOrElse((existingCourse -> {
            repo.delete(existingCourse);
        }), () -> {throw new CourseNotFoundException("Course not found");});

        return "Course deleted successfully";
    }
}
