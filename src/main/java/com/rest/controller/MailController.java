//package com.rest.controller;
//
//import com.rest.entity.Student;
//import com.rest.entity.Teacher;
//import com.rest.service.StudentService;
//import com.rest.service.TeacherService;
//import com.rest.service.TeamService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class MailController {
//    @Autowired
//    private JavaMailSender javaMailSender;
//    @Autowired
//    private StudentService studentService;
//    @Autowired
//    private TeacherService teacherService;
//    @GetMapping("send")
//    public String send(){
//        SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
//        simpleMailMessage.setFrom("1010410164@qq.com");
//        simpleMailMessage.setTo("491158566@qq.com");
//        simpleMailMessage.setText("This is an email for Test");
//        simpleMailMessage.setSubject("Hello YueYue");
//        javaMailSender.send(simpleMailMessage);
//        return "1";
//    }
//    @GetMapping(value = "/password")
//    public HttpStatus userPassword(String account){
//        Teacher teacher=teacherService.findByAccount(account);
//        Student student=studentService.findByAccount(account);
//        if(student!=null){
//            SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
//            simpleMailMessage.setFrom("1010410164@qq.com");
//            simpleMailMessage.setTo(student.getEmail());
//            simpleMailMessage.setText(student.getPassword());
//            simpleMailMessage.setSubject("This is your password");
//            javaMailSender.send(simpleMailMessage);
//            return HttpStatus.OK;
//        }
//        if (teacher!=null){
//            SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
//            simpleMailMessage.setFrom("1010410164@qq.com");
//            simpleMailMessage.setTo(teacher.getEmail());
//            simpleMailMessage.setText(teacher.getPassword());
//            simpleMailMessage.setSubject("This is your password");
//            javaMailSender.send(simpleMailMessage);
//            return HttpStatus.OK;
//        }
//        return HttpStatus.NOT_FOUND;
//    }
//}
