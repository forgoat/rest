package com.rest.controller;
import com.rest.entity.Teacher;
import com.rest.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping(value = "teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @GetMapping(value = "/")
    public List<Teacher> teacherList() {
        return teacherService.teacherList();
    }

    @PostMapping(value = "/")
    public int createTeacher(Teacher teacher) {
        return teacherService.createTeacher(teacher);
    }
    @GetMapping(value = "information")
    public Teacher findById(Long id){
        return teacherService.findById(id);
    }
}
