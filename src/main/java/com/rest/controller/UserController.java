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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.rest.entity.User;

import javax.servlet.http.HttpServletRequest;

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
     * @param request
     * @return
     */
    @PostMapping(value = "login",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseEntity<Object> Login(String account, String password, HttpServletRequest request){
        Teacher teacher=teacherService.findByAccount(account);
        if(teacher!=null&&teacher.getPassword().equals(password)){
            User user=new User(teacher);
//            HttpSession session=request.getSession();//这就是session的创建
//            session.setAttribute("id",user.getId());
//            session.setAttribute("account",user.getAccount());
//            session.setAttribute("rolename",user.getRole());
//            Object acc=session.getAttribute("account");
//
//            System.out.println(acc);
            HttpStatus httpStatus=HttpStatus.OK;
            return new ResponseEntity<Object>(user,httpStatus);
        }
        else{
            Student student=studentService.findByAccount(account);
            if(student!=null&&student.getPassword().equals(password)){
                User user=new User(student);
//                HttpSession session=request.getSession();//这就是session的创建
//                session.setAttribute("id",user.getId());
//                session.setAttribute("account",user.getAccount());
//                session.setAttribute("rolename",user.getRole());
//                Object acc=session.getAttribute("account");
//                System.out.println(acc);
                HttpStatus httpStatus=HttpStatus.OK;
                return new ResponseEntity<Object>(user,httpStatus);
            }
            else{
                HttpStatus httpStatus=HttpStatus.NOT_FOUND;
                return new ResponseEntity<Object>(null,httpStatus);
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
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @RequestMapping("/test1")
    public String test1() {
        return "test1";
    }
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping("/test2")
    public String test2()
    {
        return "test2";
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
