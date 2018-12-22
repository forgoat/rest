package com.rest.controller;

import com.rest.entity.Student;
import com.rest.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Student>> findAllStudent(){
        List<Student> studentList=studentService.findAllStudent();
        HttpStatus httpStatus=HttpStatus.OK;
        return new ResponseEntity<List<Student>>(studentList,httpStatus);
    }

    @PutMapping(value = "active")
    public ResponseEntity<Object> active(Long id,String password,String email){
        if(studentService.actival(id,password,email)==1){
            Student student=studentService.findById(id);
            HttpStatus httpStatus=HttpStatus.OK;
            return new ResponseEntity<Object>(student,httpStatus);
        }
        else {
            HttpStatus httpStatus=HttpStatus.NOT_FOUND;
            return new ResponseEntity<Object>(null,httpStatus);
        }
    }

    @DeleteMapping(value = "/{studentId}")
    public HttpStatus delete(@PathVariable("studentId")Long studentId){
        if(studentService.delete(studentId)==1){
            return HttpStatus.OK;
        }
        else {
            return HttpStatus.BAD_REQUEST;
        }
    }
    @PutMapping(value = "{studentId}/password")
    public ResponseEntity<Object> changePassword(@PathVariable("studentId")Long studentId,String password){
        if(studentService.updatePassword(studentId,password)==1){
            Student student=studentService.findById(studentId);
            HttpStatus httpStatus=HttpStatus.OK;
            return new ResponseEntity<Object>(student,httpStatus);
        }
        else {
            HttpStatus httpStatus=HttpStatus.NOT_FOUND;
            return new ResponseEntity<Object>(null,httpStatus);
        }
    }
    @GetMapping(value = "searchstudent")
    public ResponseEntity<Object> search(String account,String student_name){
        Student student=studentService.search(account,student_name);
        HttpStatus httpStatus= (student!=null)?HttpStatus.OK:HttpStatus.NOT_FOUND;
        return new ResponseEntity<Object>(student,httpStatus);
    }
    @PutMapping(value = "{studentId}/information")
    public HttpStatus updateInfo(@PathVariable("studentId")Long studentId,String account,String email,String student_name,Integer sex){
        if(studentService.updateInfo(studentId,account,email,student_name,sex)==1){
            return HttpStatus.OK;
        }
        else {
            return HttpStatus.NOT_FOUND;
        }
    }
}
