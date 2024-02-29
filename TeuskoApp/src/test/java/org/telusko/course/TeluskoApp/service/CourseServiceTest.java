package org.telusko.course.TeluskoApp.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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
    Course course;

    @BeforeEach
    void setUp() {
        course = new Course("Java", "Java Course", "3", "1000");
        course.setId(1);
        repo.save(course);
    }

    @Test
    void getAllCoursesTest() {
        when(repo.findAll()).thenReturn(Stream.of(course).collect(Collectors.toList()));
        assertEquals(1, courseService.getAllCourses().size());
    }

    @Test
    void getCourseByIdTest(){
        when(repo.findById(1)).thenReturn(Optional.of(course));
        assertEquals(course, courseService.getCourseById(1));
    }

    @Test
    void getCourseByIdNotFoundTest(){
        when(repo.findById(2)).thenThrow(new CourseNotFoundException("Course not found"));
        assertThrows(CourseNotFoundException.class, () -> courseService.getCourseById(2));
    }

    @Test
    void getCourseByName(){
        String courseName = "Java";
        when(repo.findByCourseName(courseName))
                .thenReturn(Optional.of(course));
        assertEquals(course, courseService.getByCourseName(courseName));
    }

    @Test
    void getCourseByNameNotFound(){
        String courseName = "Python";
        when(repo.findByCourseName(courseName)).thenThrow(new CourseNotFoundException("Course not found"));
        assertThrows(CourseNotFoundException.class, () -> courseService.getByCourseName(courseName));
    }

    @Test
    void addCourseTest(){
        when(repo.save(course)).thenReturn(course);
        assertEquals("Course added successfully", courseService.addCourse(course));
    }

    @Test
    void updateCourseTest(){
        when(repo.findById(1)).thenReturn(Optional.of(course));
        assertEquals("Course updated successfully", courseService.updateCourse(course));
    }

    @Test
    void updateCourseNotFoundTest(){
        when(repo.findById(2)).thenThrow(new CourseNotFoundException("Course not found"));
        assertThrows(CourseNotFoundException.class, () -> courseService.updateCourse(course));
    }

    @Test
    void deleteCourseByIdTest(){
        when(repo.findById(1)).thenReturn(Optional.of(course));
        assertEquals("Course deleted successfully", courseService.deleteById(1));
    }

    @AfterEach
    void tearDown() {
        course=null;
        repo.deleteAll();
    }
}