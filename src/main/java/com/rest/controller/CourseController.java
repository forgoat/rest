package com.rest.controller;

import com.rest.entity.Course;
import com.rest.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "course")
public class CourseController {
    @Autowired
    private CourseService courseService;
    @GetMapping(value = "")
    public List<Course> findAllCourse(){
        return courseService.findAllCourse();
    }
    @PostMapping(value = "")
    public String saveCourse(Course course){
        if(courseService.saveCourse(course)==1){
            return "201";
        }
        else {
            return "403";
        }
    }
    @GetMapping(value = "{courseId}")
    public Course findById(@PathVariable("courseId") Long courseId){
        return courseService.findById(courseId);
    }
}
