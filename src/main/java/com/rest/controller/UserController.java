package com.rest.controller;

import com.rest.entity.Student;
import com.rest.entity.Teacher;
import com.rest.service.StudentService;
import com.rest.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.rest.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(value = "user")
public class UserController {
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StudentService studentService;
    @PostMapping(value = "login",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseEntity<Object> Login(String account, String password, HttpServletRequest request){
        Teacher teacher=teacherService.findByAccount(account);
        if(teacher!=null&&teacher.getPassword().equals(password)){
            User user=new User(teacher);
            HttpSession session=request.getSession();//这就是session的创建
            session.setAttribute("id",user.getId());
            session.setAttribute("rolename",user.getRoleName());
            HttpStatus httpStatus=HttpStatus.OK;
            return new ResponseEntity<Object>(user,httpStatus);
        }
        else{
            Student student=studentService.findByAccount(account);
            if(student!=null&&student.getPassword().equals(password)){
                User user=new User(student);
                HttpSession session=request.getSession();//这就是session的创建
                session.setAttribute("id",user.getId());
                session.setAttribute("rolename",user.getRoleName());
                HttpStatus httpStatus=HttpStatus.OK;
                return new ResponseEntity<Object>(user,httpStatus);
            }
            else{
                HttpStatus httpStatus=HttpStatus.NOT_FOUND;
                return new ResponseEntity<Object>(null,httpStatus);
            }
        }
    }
    @GetMapping(value = "information")
    public ResponseEntity<Object> information(Long id,String role){
//        HttpSession session=request.getSession();//这就是session的创建
//        Object sid=session.getAttribute("id");
//        String strid=String.valueOf(sid);
//        Long id=Long.valueOf(strid);
//        Object orole=session.getAttribute("rolename");
//        String role=String.valueOf(orole);
        if (role.equals("student")){
            Student student=studentService.findById(id);
            User user=new User(student);
            HttpStatus httpStatus=HttpStatus.OK;
            return new ResponseEntity<Object>(user,httpStatus);
        }
        else{
            Teacher teacher=teacherService.findById(id);
            User user=new User(teacher);
            HttpStatus httpStatus=HttpStatus.OK;
            return new ResponseEntity<Object>(user,httpStatus);
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
    public HttpStatus updatePassword(Long id, String password, String role){
        if(role.equals("student")){
            if(studentService.updatePassword(id,password)==1){
                return HttpStatus.OK;
            }
            else{
                return HttpStatus.BAD_REQUEST;
            }
        }
        else{
            if(teacherService.updatePassword(id,password)==1){
                return HttpStatus.OK;
            }
            else {
                return HttpStatus.BAD_REQUEST;
            }
        }
    }
    @PutMapping(value = "email")
    public HttpStatus updateEmail(Long id,String email,String role){
        if(role.equals("student")){
            if(studentService.updateEmail(id,email)==1){
                return HttpStatus.OK;
            }
            else {
                return HttpStatus.BAD_REQUEST;
            }
        }
        else {
            if(teacherService.updateEmail(id,email)==1){
                return HttpStatus.OK;
            }
            else {
                return HttpStatus.BAD_REQUEST;
            }
        }
    }
}
