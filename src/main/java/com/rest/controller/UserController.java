package com.rest.controller;

import com.rest.entity.Student;
import com.rest.entity.Teacher;
import com.rest.service.StudentService;
import com.rest.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.rest.entity.User;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(value = "user")
public class UserController {
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private JavaMailSender javaMailSender;

    /**
     * 用户登录
     * @param account
     * @param password
     * @return
     */
    @PostMapping(value = "login")
    public ResponseEntity<User> Login(String account,String password){
        Teacher teacher=teacherService.findByAccount(account);
        if(teacher!=null&&teacher.getPassword().equals(password)){
            User user=new User(teacher);
            HttpStatus httpStatus=HttpStatus.OK;
            return new ResponseEntity<User>(user,httpStatus);
        }
        else{
            Student student=studentService.findByAccount(account);
            if(student!=null&&student.getPassword().equals(password)){
                User user=new User(student);
                HttpStatus httpStatus=HttpStatus.OK;
                return new ResponseEntity<User>(user,httpStatus);
            }
            else{
                HttpStatus httpStatus=HttpStatus.NOT_FOUND;
                return new ResponseEntity<User>(new User(),httpStatus);
            }
        }
    }

    /**
     * 用户信息
     * @param id
     * @param role
     * @return
     */
    @GetMapping(value = "information")
    public ResponseEntity<Object> information(Long id,String role){
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
//    @GetMapping(value = "password")
//    public User password(Long id,String role){
//        if(role.equals("student")){
//            Student student=studentService.findById(id);
//            User user=new User(student);
//            return user;
//        }
//        else {
//            Teacher teacher=teacherService.findById(id);
//            User user=new User(teacher);
//            return user;
//        }
//    }

    /**
     * 修改密码
     * @param id
     * @param password
     * @param role
     * @return
     */
    @PutMapping(value = "password")
    public HttpStatus updatePassword(Long id, String password, String role,HttpSession session){
        //Object account= session.getAttribute("account");

        //System.out.println("your account=="+account);
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

    /**
     * 修改邮箱
     * @param id
     * @param email
     * @param role
     * @return
     */
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
    /**
     * 发送密码到邮箱
     * @param account
     * @return
     */
    @GetMapping(value = "/password")
    public HttpStatus userPassword(String account){
        Teacher teacher=teacherService.findByAccount(account);
        Student student=studentService.findByAccount(account);
        SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
        simpleMailMessage.setFrom("1010410164@qq.com");
        if(student!=null){
            simpleMailMessage.setTo(student.getEmail());
            simpleMailMessage.setText(student.getPassword());
            simpleMailMessage.setSubject("This is your password");
            javaMailSender.send(simpleMailMessage);
            return HttpStatus.OK;
        }
        if (teacher!=null){
            simpleMailMessage.setTo(teacher.getEmail());
            simpleMailMessage.setText(teacher.getPassword());
            simpleMailMessage.setSubject("This is your password");
            javaMailSender.send(simpleMailMessage);
            return HttpStatus.OK;
        }
        return HttpStatus.NOT_FOUND;
    }
}
