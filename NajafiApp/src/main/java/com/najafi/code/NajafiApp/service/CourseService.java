package com.najafi.code.NajafiApp.service;

import com.najafi.code.NajafiApp.model.Course;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class CourseService {

    private static final String ADD_COURSE = "http://localhost:8081/Telusko/course/add";
    private static final String GET_ALL_COURSE = "http://localhost:8081/Telusko/course/all";
    private static final String GET_COURSE_BY_ID = "http://localhost:8081/Telusko/course/id/{id}";
    private static final String UPDATE_COURSE = "http://localhost:8081/Telusko/course/update";
    private static final String DELETE_COURSE = "http://localhost:8081/Telusko/course/delete/{id}";


    /**
     * Retrieves all courses from the web client.
     * @return a list of courses
     */
    public List<Course> getAllCourse() {
        WebClient webClient = WebClient.create();   // Create a web client
        return webClient.get()    // Perform a GET request
                .uri(GET_ALL_COURSE)    // set the URI for the request
                .retrieve()     //Retrieve the response
                .bodyToMono(new ParameterizedTypeReference<List<Course>>() {})  // Convert the response to list of courses
                .block();   // block and retrieve the result
    }

    /**
     * Adds a course using the given course object
     * @param course the course to be added
     * @return the response from the server as a string
     */
    public String addCourse(Course course) {
        WebClient webClient = WebClient.create();
        return webClient.post()
                .uri(ADD_COURSE)
                .header("Content-Type", "application/json")
                .bodyValue(course)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    /*
    * Retrieves a course by id from web client
    * @param id is used get course by its id
    * @return a Course fetched using web client
    * */
    public Course getCourseById(Integer id) {
       WebClient webClient = WebClient.create();
       return webClient.get()
               .uri(GET_COURSE_BY_ID, id)
               .retrieve()
               .bodyToMono(Course.class)
               .block();
    }


    public String deleteCourse(Integer id) {
      return WebClient.create()
               .delete()
               .uri(DELETE_COURSE, id)
               .retrieve()
               .bodyToMono(String.class)
               .block();
    }
}
