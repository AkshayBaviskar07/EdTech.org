package org.telusko.course.TeluskoApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.telusko.course.TeluskoApp.model.Course;
import org.telusko.course.TeluskoApp.service.CourseService;

import java.util.List;

@RestController
@RequestMapping("course")
public class CourseController {

    @Autowired
    private CourseService service;

    @GetMapping("all")
    public ResponseEntity<List<Course>> getAllCourses(){
        return service.getAllCourses();
    }

    @PostMapping("add")
    public ResponseEntity<String> addCourse(@RequestBody Course course){
        return service.addCourse(course);
    }

    @GetMapping("id/{id}")
    public ResponseEntity<Course> getById(@PathVariable Integer id){
        return new ResponseEntity<>(service.getCourseById(id), HttpStatus.OK);
    }

    @GetMapping("name/{courseName}")
    public ResponseEntity<Course> getByCourseName(@PathVariable String courseName){
        return new ResponseEntity<>(service.getByCourseName(courseName), HttpStatus.OK);
    }

    @PutMapping("update")
    public ResponseEntity<String> updateCourse(@RequestBody Course course){
        return new ResponseEntity<>(service.updateCourse(course), HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Integer id){
        return new ResponseEntity<>(service.deleteById(id), HttpStatus.OK);
    }
}
