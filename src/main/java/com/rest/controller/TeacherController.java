package com.rest.controller;
import com.rest.entity.Teacher;
import com.rest.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String createTeacher(Teacher teacher) {
        if(teacherService.createTeacher(teacher)==1){
            return "200";
        }
        else {
            return "400";
        }
    }
    @PutMapping(value = "active")
    public String actival(Long id,String password){
        if(teacherService.actival(id,password)==1){
            return "200";
        }
        else {
            return "400";
        }
    }
    @DeleteMapping(value = "{teacherId}")
    public String delete(@PathVariable("teacherId")Long teacherId){
        if(teacherService.delete(teacherId)==1){
            return "200";
        }
        else {
            return "404";
        }
    }
    @PutMapping(value = "{teacherId}/password")
    public String changePassword(@PathVariable("teacherId")Long teacherId,String password){
        if(teacherService.updatePassword(teacherId,password)==1){
            return "200";
        }
        else {
            return "404";
        }
    }
}
