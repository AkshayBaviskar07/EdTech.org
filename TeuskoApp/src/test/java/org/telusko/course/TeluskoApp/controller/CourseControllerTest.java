package org.telusko.course.TeluskoApp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.telusko.course.TeluskoApp.model.Course;
import org.telusko.course.TeluskoApp.service.CourseService;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CourseController.class)
class CourseControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CourseService courseService;
    Course course1 = null;
    Course course2 = null;


    @BeforeEach
    void setUp() {
        // Initialize course1 with details
        course1 = new Course("Java", "Java Course", "3", "1000");
        course1.setId(1);
        // Initialize course2 with details
        course2 = new Course("Python", "Python Course", "3", "1000");
        course2.setId(2);
    }

    /**
     * Test the getAllCourses() method in the CourseController class.
     *@throws Exception if an error occurs during the test.
     */
    @Test
    void testGetAllCourses() throws Exception {
        // Mock the service layer
        when(courseService.getAllCourses()).thenReturn(List.of(course1, course2));

        // Perform the GET request
        this.mockMvc.perform(get("/course/all"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].courseName").value("Java"))
                .andExpect(jsonPath("$[1].courseName").value("Python"));
    }

    /**
     * Test the behavior of getting a course by ID.
     * @throws Exception    if an error occurs
     */
    @Test
    void testGetCourseById() throws Exception {
        // Mock the service layer
        when(courseService.getCourseById(1)).thenReturn(course1);

        // Perform the GET request
        this.mockMvc.perform(get("/course/id/1"))
                .andExpect(status().isOk()).andDo(print());
    }

    /**
     * Test for getting a course by name.
     *
     * @throws Exception
     */
    @Test
    void testGetCourseByName() throws Exception {
        // Mock the service layer
        when(courseService.getByCourseName("Java")).thenReturn(course1);

        // Perform the GET request
        this.mockMvc.perform(get("/course/name/Java"))
                .andExpect(status().isOk()).andDo(print());
    }

    /**
     * Test the addition of a course.
     *
     * @throws Exception   if an error occurs during the test
     */
    @Test
    void testAddCourse() throws Exception {
        // Convert object into JSON
        ObjectMapper mapper = new ObjectMapper();
        String requestJson = mapper.writeValueAsString(course1);

        // Mock the service layer
        when(courseService.addCourse(course1)).thenReturn("Course added successfully");

        // Perform the POST request
        this.mockMvc.perform(post("/course/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isOk()).andDo(print());
    }

    /**
     * Test for updating the course test.
     */
    @Test
    void testUpdateCourse() throws Exception {
      //  Convert the object into JSON
        ObjectMapper mapper = new ObjectMapper();
        String requestJson = mapper.writeValueAsString(course1);

        // Mock the service layer
        when(courseService.updateCourse(course1)).thenReturn("Course updated successfully");

        // Perform the PUT operation
        this.mockMvc.perform(put("/course/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andDo(print()).andExpect(status().isOk());
    }

    /**
     * Test the deleteById method by mocking the service layer and performing the DELETE request
     * @throws Exception
     */
    @Test
    void testDeleteById() throws Exception {
        // Mock the service layer
        when(courseService.deleteById(1)).thenReturn("Course deleted successfully");

        //Perform the DELETE request
        this.mockMvc.perform(delete("/course/delete/1"))
                .andExpect(status().isOk()).andDo(print());
    }

    /**
     * This method is called after each test to clean up resources.
     */
    @AfterEach
    void tearDown() {
        // Set the course references to null to release resources
        course1 = null;
        course2 = null;
    }
}