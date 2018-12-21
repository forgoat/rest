package com.rest.controller;

import com.rest.entity.Student;
import com.rest.entity.Teacher;
import com.rest.service.StudentService;
import com.rest.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.rest.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "user")
public class UserController {
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StudentService studentService;
    @PostMapping(value = "login",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object Login(String account, String password,HttpServletRequest request,HttpServletResponse response){
        Teacher teacher=teacherService.findByAccount(account);
        if(teacher!=null&&teacher.getPassword().equals(password)){
            User user=new User(teacher);
            response.setStatus(200);
            return user;
        }
        else{
            Student student=studentService.findByAccount(account);
            if(student!=null&&student.getPassword().equals(password)){
                User user=new User(student);
                response.setStatus(200);
                return user;
            }
            else{
                response.setStatus(400);
                return response;
            }
        }
    }
    @GetMapping(value = "information")
    public User information(Long id,String role){
        if (role.equals("student")){
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
    @GetMapping(value = "password")
    public User password(Long id,String role){
        if(role.equals("student")){
            Student student=studentService.findById(id);
            User user=new User(student);
            return user;
        }
        else {
            Teacher teacher=teacherService.findById(id);
            User user=new User(teacher);
            return user;
        }
    }
    @PutMapping(value = "password")
    public String updatePassword(Long id, String password, String role){
        if(role.equals("student")){
            if(studentService.updatePassword(id,password)==1){
                return "200";
            }
            else{
                return "400";
            }
        }
        else{
            if(teacherService.updatePassword(id,password)==1){
                return "200";
            }
            else {
                return "400";
            }
        }
    }
    @PutMapping(value = "email")
    public String updateEmail(Long id,String email,String role){
        if(role.equals("student")){
            if(studentService.updateEmail(id,email)==1){
                return "200";
            }
            else {
                return "400";
            }
        }
        else {
            if(teacherService.updateEmail(id,email)==1){
                return "200";
            }
            else {
                return "400";
            }
        }
    }
}
