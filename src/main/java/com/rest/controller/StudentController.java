package com.rest.controller;

import com.rest.entity.Student;
import com.rest.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping(value ="" )
    public List<Student> findAllStudent(){
        return studentService.findAllStudent();
    }

    @PutMapping(value = "active")
    public String active(Long id,String password,String email){
        if(studentService.actival(id,password,email)==1){
            return "200";
        }
        else {
            return "400";
        }
    }
    @GetMapping(value = "queryStudent")
    public List<Student> queryStudent(Student student){
        return studentService.queryStudent(student);
    }

    @PostMapping(value = "")
    public String add(Student student)
    {
        if(studentService.add(student)==1){
            return "200";
        }
        else {
            return "400";
        }
    }
    @DeleteMapping(value = "/{studentId}")
    public String delete(@PathVariable("studentId")Long studentId){
        if(studentService.delete(studentId)==1){
            return "200";
        }
        else {
            return "404";
        }
    }
    @PutMapping(value = "{studentId}/password")
    public String changePassword(@PathVariable("studentId")Long studentId,String password){
        if(studentService.updatePassword(studentId,password)==1){
            return "200";
        }
        else {
            return "404";
        }
    }
}
