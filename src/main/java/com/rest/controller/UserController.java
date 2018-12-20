package com.rest.controller;

import com.rest.entity.Student;
import com.rest.entity.Teacher;
import com.rest.service.StudentService;
import com.rest.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "user")
public class UserController {
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StudentService studentService;
    @PostMapping(value = "login")
    public String Login(String account,String password){
        Teacher teacher=teacherService.findByAccount(account);
        if(teacher!=null&&teacher.getPassword().equals(password)){
            return "teacher";
        }
        else{
            Student student=studentService.findByAccount(account);
            if(student!=null&&student.getPassword().equals(password)){
                return "student";
            }
            else{
                return "false";
            }
        }
    }
}
