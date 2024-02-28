package com.najafi.code.NajafiApp.controller;

import com.najafi.code.NajafiApp.model.Course;
import com.najafi.code.NajafiApp.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("NajaFi")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/list")
    public String getAllCourse(Model model) {
        List<Course>  courses = courseService.getAllCourse();
        courses.forEach(System.out::println);
        model.addAttribute("courses", courses);
        return "index";
    }

    @GetMapping("/showForm")
    public String registerForm(Model model){
        Course course = new Course();
        model.addAttribute("course", course);
        return "register-form";
    }

    @PostMapping("add-course")
    public String addCourse(@ModelAttribute Course course, Model model){
        String message = courseService.addCourse(course);
        model.addAttribute("message", message);
        return "redirect:/NajaFi/list";
    }

    @GetMapping("update")
    public String updateCourse(@RequestParam Integer id, Model model){
        Course course = courseService.getCourseById(id);
        model.addAttribute("course", course);
        return "register-form";
    }

    @GetMapping("delete")
    public String deleteCourse(@RequestParam Integer id, Model model){
        String message = courseService.deleteCourse(id);
        model.addAttribute("message", message);
        return "redirect:/NajaFi/list";
    }

}
