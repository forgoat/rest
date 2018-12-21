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
    public int createTeacher(Teacher teacher) {
        return teacherService.createTeacher(teacher);
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
}
