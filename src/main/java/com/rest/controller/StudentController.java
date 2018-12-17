package com.rest.controller;

import com.rest.entity.Student;
import com.rest.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping(value ="queryAllStudent" )
    public List<Student> queryAllStudent(){
        return studentService.queryAllStudent();
    }

    @GetMapping(value = "queryStudent")
    public List<Student> queryStudent(Student student){
        return studentService.queryStudent(student);
    }

    @PostMapping(value = "insertStudent")
    public int insertStudent(Student student){
        return studentService.insertStudent(student);
    }



}
