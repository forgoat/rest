package com.rest.controller;

import com.rest.entity.Student;
import com.rest.entity.Teacher;
import com.rest.service.StudentService;
import com.rest.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.rest.entity.User;
@RestController
@RequestMapping(value = "user")
public class UserController {
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StudentService studentService;
    @PostMapping(value = "login")
    public User Login(String account,String password){
        Teacher teacher=teacherService.findByAccount(account);
        if(teacher!=null&&teacher.getPassword().equals(password)){
            User user=new User(teacher);
            return user;
        }
        else{
            Student student=studentService.findByAccount(account);
            if(student!=null&&student.getPassword().equals(password)){
                User user=new User(student);
                return user;
            }
            else{
                return null;
            }
        }
    }
    @GetMapping(value = "information")
    public User information(Long id,String roleName){
        if (roleName.equals("student")){
            Student student=studentService.findById(id);
            User user=new User(student);
            return user;
        }
        else{
            Teacher teacher=teacherService.findById(id);
            User user=new User(teacher);
            return user;
        }
    }
}
