package org.telusko.course.TeluskoApp.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.telusko.course.TeluskoApp.dao.CourseRepo;
import org.telusko.course.TeluskoApp.exceptions.CourseNotFoundException;
import org.telusko.course.TeluskoApp.model.Course;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class CourseServiceTest {

    @Autowired
    private CourseService courseService;
    @MockBean
    private CourseRepo repo;

    @Test
    void getAllCoursesTest() {
        when(repo.findAll()).thenReturn(Stream.of(
                new Course("Java", "Java Course", "3", "1000"),
                new Course("Spring", "Spring Course", "3", "1000")
        ).collect(Collectors.toList()));

        assertEquals(2, courseService.getAllCourses().size());
    }

    @Test
    void getCourseByIdTest(){
        Course course = new Course("Java", "Java Course", "3", "1000");
        course.setId(1);
        when(repo.findById(1)).thenReturn(Optional.of(course));
        assertEquals(course, courseService.getCourseById(1));
    }

    @Test
    void getCourseByIdNotFoundTest(){
        Course course = new Course("Java", "Java Course", "3", "1000");
        course.setId(1);

        when(repo.findById(2)).thenThrow(new CourseNotFoundException("Course not found"));
        assertThrows(CourseNotFoundException.class, () -> courseService.getCourseById(2));
    }

    @Test
    void getCourseByName(){
        String courseName = "Java";
        Course course = new Course("Java", "Java Course", "3", "1000");
        when(repo.findByCourseName(courseName))
                .thenReturn(Optional.of(course));

        assertEquals(course, courseService.getByCourseName(courseName));
    }

    @Test
    void addCourseTest(){
        Course course = new Course("Java", "Java Course", "3", "1000");
        when(repo.save(course)).thenReturn(course);

        assertEquals("Course added successfully", courseService.addCourse(course));
    }

    @Test
    void updateCourseTest(){
        Course existingCourse = new Course();
        existingCourse.setId(1);
        existingCourse.setCourseName("Java");
        existingCourse.setCourseDescription("Java Course");
        existingCourse.setCourseDuration("3");
        existingCourse.setCourseFees("1000");

        when(repo.findById(1)).thenReturn(Optional.of(existingCourse));


        assertEquals("Course updated successfully", courseService.updateCourse(existingCourse));
    }

    @Test
    void updateCourseNotFoundTest(){
        Course existingCourse = new Course();
        existingCourse.setId(1);
        existingCourse.setCourseName("Java");
        existingCourse.setCourseDescription("Java Course");
        existingCourse.setCourseDuration("3");
        existingCourse.setCourseFees("1000");

        when(repo.findById(2)).thenThrow(new CourseNotFoundException("Course not found"));

        assertThrows(CourseNotFoundException.class, () -> courseService.updateCourse(existingCourse));
    }

    @Test
    void deleteCourseByIdTest(){
        Course existingCourse = new Course();
        existingCourse.setId(1);
        existingCourse.setCourseName("Java");
        existingCourse.setCourseDescription("Java Course");
        existingCourse.setCourseDuration("3");
        existingCourse.setCourseFees("1000");

        when(repo.findById(1)).thenReturn(Optional.of(existingCourse));

        assertEquals("Course deleted successfully", courseService.deleteById(1));
    }
}