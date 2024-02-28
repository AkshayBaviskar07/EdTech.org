package com.najafi.code.NajafiApp.service;

import com.najafi.code.NajafiApp.model.Course;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CourseService {

    private static final String ADD_COURSE = "http://localhost:8081/Telusko/course/add";
    private static final String GET_ALL_COURSE = "http://localhost:8081/Telusko/course/all";
    private static final String GET_COURSE_BY_ID = "http://localhost:8081/Telusko/course/id/{id}";
    private static final String UPDATE_COURSE = "http://localhost:8081/Telusko/course/update";
    private static final String DELETE_COURSE = "http://localhost:8081/Telusko/course/delete/{id}";


    public List<Course> getAllCourse() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange(
                GET_ALL_COURSE,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Course>>() {}
        ).getBody();
    }

    public String addCourse(Course course) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(ADD_COURSE, course, String.class).getBody();
    }

    public Course getCourseById(Integer id) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange(
                GET_COURSE_BY_ID,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Course>() {},
                id).getBody();
    }

    public String deleteCourse(Integer id) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange(
                DELETE_COURSE,
                HttpMethod.DELETE,
                null,
                String.class,
                id).getBody();
    }
}
