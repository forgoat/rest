package com.rest.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.rest.entity.Course;
import com.rest.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "course")
public class CourseController {
    @Autowired
    private CourseService courseService;
    @GetMapping(value = "")
    public ResponseEntity<List<Course>> findAllCourse(){
        List<Course> courseList=courseService.findAllCourse();
        HttpStatus httpStatus=(courseList.isEmpty())?HttpStatus.NOT_FOUND:HttpStatus.OK;
        return new ResponseEntity<List<Course>>(courseList,httpStatus);
    }
    @PostMapping(value = "")
    public ResponseEntity<Long> saveCourse(Course course){
        if(courseService.saveCourse(course)==1){
            return new ResponseEntity<Long>(course.getId(),HttpStatus.ACCEPTED);
        }
        else {
            Long id=new Long(0);
            return new ResponseEntity<Long>(id,HttpStatus.FORBIDDEN);
        }
    }
    @GetMapping(value = "{courseId}")
    public ResponseEntity<Course> findById(@PathVariable("courseId") Long courseId){
        Course course=courseService.findById(courseId);
        HttpStatus httpStatus=(course!=null)?HttpStatus.OK:HttpStatus.NOT_FOUND;
        ResponseEntity<Course> courseResponseEntity=new ResponseEntity<Course>(course,httpStatus);
        System.out.println(courseResponseEntity.getStatusCode());
        return courseResponseEntity;
    }
    @DeleteMapping(value = "{courseId}")
    public HttpStatus deleteById(@PathVariable("courseId")Long courseId){
        if(courseService.deleteById(courseId)==1){
            return HttpStatus.OK;
        }
        else {
            return HttpStatus.NOT_FOUND;
        }
    }
}
