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
     * Retrieves all courses from the rest template..
     * @return a list of courses
     */
    public List<Course> getAllCourse() {
       RestTemplate restTemplate = new RestTemplate();
       return restTemplate.exchange(
               GET_ALL_COURSE,
               HttpMethod.GET,
               null,
               new ParameterizedTypeReference<List<Course>>() {}).getBody();
    }

    /**
     * Adds a course using the given course object
     * @param course the course to be added
     * @return the response from the server as a string
     */
    public String addCourse(Course course) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(ADD_COURSE,
                course,
                String.class).getBody();
    }

    /*
    * Retrieves a course by id from rest template
    * @param id is used get course by its id
    * @return a Course fetched using rest template
    * */
    public Course getCourseById(Integer id) {
        RestTemplate restTemplate = new RestTemplate();
        return  restTemplate.getForEntity(GET_COURSE_BY_ID,
                Course.class,
                id).getBody();
    }


    public String deleteCourse(Integer id) {
      RestTemplate restTemplate = new RestTemplate();
      return  restTemplate.exchange(DELETE_COURSE,
              HttpMethod.DELETE,
              null,
              String.class,
              id).getBody();
    }
}
