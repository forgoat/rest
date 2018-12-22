package com.rest.controller;
import com.rest.entity.Teacher;
import com.rest.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @GetMapping(value = "")
    public List<Teacher> teacherList() {
        return teacherService.teacherList();
    }

    @PostMapping(value = "")
    public ResponseEntity<Object> createTeacher(Teacher teacher) {
        if(teacherService.createTeacher(teacher)==1){
            return new ResponseEntity<Object>(teacher, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<Object>(null,HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping(value = "active")
    public ResponseEntity<Object> actival(Long id,String password){
        if(teacherService.actival(id,password)==1){
            Teacher teacher=teacherService.findById(id);
            return new ResponseEntity<Object>(teacher,HttpStatus.OK);
        }
        else {
            return new ResponseEntity<Object>(null,HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping(value = "{teacherId}")
    public HttpStatus delete(@PathVariable("teacherId")Long teacherId){
        if(teacherService.delete(teacherId)==1){
            return HttpStatus.OK;
        }
        else {
            return HttpStatus.NOT_FOUND;
        }
    }
    @PutMapping(value = "{teacherId}/password")
    public ResponseEntity<Object> changePassword(@PathVariable("teacherId")Long teacherId,String password){
        if(teacherService.updatePassword(teacherId,password)==1){
            Teacher teacher=teacherService.findById(teacherId);
            return new ResponseEntity<Object>(teacher,HttpStatus.OK);
        }
        else {
            return new ResponseEntity<Object>(null,HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping(value = "searchteacher")
    public ResponseEntity<List<Teacher>> search(String account,String teacher_name){
        List<Teacher> teacherList=teacherService.search(account,teacher_name);
        if(!teacherList.isEmpty()){
            return new ResponseEntity<List<Teacher>>(teacherList,HttpStatus.OK);
        }
        else {
            return new ResponseEntity<List<Teacher>>(teacherList,HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping(value = "{teacherId}/information")
    public ResponseEntity<Object> updateInfo(@PathVariable("teacherId")Long teacherId,String account,String email,String teacher_name){
        if(teacherService.updateInfo(teacherId,account,email,teacher_name)==1){
            Teacher teacher=teacherService.findById(teacherId);
            return new ResponseEntity<Object>(teacher,HttpStatus.OK);
        }
        else {
            return new ResponseEntity<Object>(null,HttpStatus.NOT_FOUND);
        }
    }
}
