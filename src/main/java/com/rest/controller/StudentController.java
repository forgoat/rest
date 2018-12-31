package com.rest.controller;

import com.rest.entity.Student;
import com.rest.entity.StudentData;
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

    /**
     * 获取所有学生
     * @return
     */
    @GetMapping(value ="" )
    public StudentData findAllStudent(){
        List<Student> studentList=studentService.findAllStudent();
        StudentData studentData=new StudentData(studentList);
        return studentData;
    }

    /**
     * 激活学生账号
     * @param id
     * @param password
     * @param email
     * @return
     */
    @PutMapping(value = "active")
    public ResponseEntity<Object> active(Long id,String password,String email){
        if(studentService.actival(id,password,email)==1){
            Student student=studentService.findById(id);
            HttpStatus httpStatus=HttpStatus.OK;
            System.out.println(httpStatus);
            return new ResponseEntity<Object>(student,httpStatus);
        }
        else {
            HttpStatus httpStatus=HttpStatus.NOT_FOUND;
            System.out.println(httpStatus);
            return new ResponseEntity<Object>(null,httpStatus);
        }
    }

    /**
     * 删除学生
     * @param studentId
     * @return
     */
    @DeleteMapping(value = "/{studentId}")
    public HttpStatus delete(@PathVariable("studentId")Long studentId){
        if(studentService.delete(studentId)==1){
            return HttpStatus.OK;
        }
        else {
            return HttpStatus.BAD_REQUEST;
        }
    }

    /**
     * 修改密码
     * @param studentId
     * @param password
     * @return
     */
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

    /**
     * 搜索学生
     * @param account
     * @param studentName
     * @return
     */
    @GetMapping(value = "searchstudent")
    public ResponseEntity<Object> search(String account,String studentName){
        Student student=studentService.search(account,studentName);
        HttpStatus httpStatus= (student!=null)?HttpStatus.OK:HttpStatus.NOT_FOUND;
        return new ResponseEntity<Object>(student,httpStatus);
    }

    /**
     * 动态修改学生信息
     * @param studentId
     * @param account
     * @param email
     * @param studentName
     * @return
     */
    @PutMapping(value = "{studentId}/information")
    public HttpStatus updateInfo(@PathVariable("studentId")Long studentId,String account,String email,String studentName){
        if(studentService.updateInfo(studentId,account,email,studentName)==1){
            return HttpStatus.OK;
        }
        else {
            return HttpStatus.NOT_FOUND;
        }
    }

   //测试
    @GetMapping(value = "queryKlassByStudentIdCourseId")
    public Long queryKlassByStudentIdCourseId(Long studentId,Long courseId){
        return studentService.queryKlassByStudentIdCourseId(studentId,courseId);
    }

    /**
     * 通过id查找学生
     * @param id
     * @return
     */
    @GetMapping(value = "{studentId}")
    public Student findStudentById(@PathVariable("studentId") Long id){
        return studentService.findById(id);
    }

}
